package jsf;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;

// Default to MinHeap Priority Queue
public class PriorityQueue<T> extends AbstractQueue<T>{
    private ArrayList<T> heap;
    public static int entryNumber = 0;
    public Comparator<T> comparator;

    public PriorityQueue(){ heap = new ArrayList();
    }

    public PriorityQueue(Comparator<T> comparator){
        heap = new ArrayList();
        this.comparator = comparator;
    }

    public boolean add(T data){
        try{
            heap.add(data);
            entryNumber ++;
            trickleUp();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void clear(){
        heap.clear();
        entryNumber = 0;
    }

    public Comparator comparator(){
        return comparator;
    }

    public boolean contains(Object o){
        return heap.contains(o);
    }

    @Override
    public Iterator iterator(){
        return new Iterator();
    }

    public boolean offer(T t){
        return add(t);
    }

    public T peek(){
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public T poll(){
        if (isEmpty()) return null;
        T returnItem = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        if (!isEmpty()) trickleDown();
        entryNumber++;
        return returnItem;
    }

    public boolean remove(Object t){
        if (heap.contains(t)){
            swap(heap.indexOf(t),0);
            entryNumber++;
            heap.remove(t);
            trickleDown();
            return true;
        }
        return false;
    }

    public int size(){
        return heap.size();
    }

    public Object[] toArray(){
        return (heap.toArray());
    }

    public Object[] toArray(Object[] tArray){
        if (tArray.length < heap.size()){
            for (int i =0; i < heap.size(); i++)
                tArray[i] = (T)heap.get(i);
            tArray[size()] = null;
            return tArray;
        }
        else if (tArray.length == heap.size()){
            for (int i =0; i < heap.size(); i++){
                tArray[i] = (T)heap.get(i);
            }
            return tArray;
        }
        else{
            return toArray();
        }
    }

    public boolean isEmpty(){
        return (heap.size() == 0);
    }

    private void swap(int index1, int index2){
        T swap = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, swap);
    }

    private void trickleUp(){
        int newIndex = heap.size() - 1;
        int parentIndex = (newIndex - 1) >> 1;
        T newItem = heap.get(newIndex);
        while (parentIndex >= 0 && comparator.compare
                (newItem, heap.get(parentIndex)) < 0) {
            swap(newIndex,parentIndex);
            newIndex = parentIndex;
            parentIndex = (parentIndex - 1) >> 1;
        }
        heap.set(newIndex, newItem);
    }

    private void trickleDown(){
        int current = 0;
        int child = getNextChild(current);
        while (child != -1 &&
                comparator.compare(
                heap.get(current), heap.get(child)) > 0){
            swap(current, child);
            current = child;
            child = getNextChild(current);
        }
    }

    private int getNextChild(int current){
        int left = (current << 1) + 1;
        int right = left + 1;
        if (right < heap.size()){
            if (comparator.compare(
                    heap.get(left), heap.get(right)) < 0) return left;
            return right;
        }
        if (left < heap.size()) return left;
        return -1;
    }
    
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<T> backupHeap = (ArrayList<T>) heap.clone();
        while (!isEmpty()){
            stringBuilder.append(poll().toString());
        }
        heap = (ArrayList<T>)backupHeap.clone();
        return stringBuilder.toString();    
    }

    private class Iterator implements java.util.Iterator {
        int index;
        int initialHeapSize;
        public Iterator(){
            index = 0;
            initialHeapSize = heap.size();
        }

        public boolean hasNext() {
            checkConcurrentModification();
            return index < heap.size();
        }

        public Object next() {
            return heap.get(index++);
        }

        private void checkConcurrentModification(){
            if (initialHeapSize != heap.size()){
                throw new ConcurrentModificationException();
            }
        }
    }
}
