package jsf;

import jsf.Commands.*;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Driver {

    // General driver to show working Priority Queue
    public static void main(String args[]) {


        Student[] veryLowPriority, lowPriority, mediumPriority, highPriority,
                veryHighPriority, zeroPriority, maxPriority, duplicates;
        jsf.PriorityQueue jimPQ;// = new jsf.PriorityQueue<Student>();

        //Comparator<Student> comparator =
        //        ((Student s1, Student s2) ->  (s1.getPriority() > s2.getPriority()) ?
        //        -1 : 1);
        Comparator<Student> comparator =
                ((Student s1, Student s2) ->  (s1.gpa > s2.gpa ?
                -1 : 1));
        jimPQ = new jsf.PriorityQueue(comparator);

        PriorityQueue priorityQueue = new PriorityQueue(comparator);

        maxPriority = new Student[3];
        veryHighPriority = new Student[3];
        highPriority = new Student[3];
        mediumPriority = new Student[3];
        lowPriority = new Student[3];
        veryLowPriority = new Student[3];
        zeroPriority = new Student[3];
        duplicates = new Student[5];

        fill("max", maxPriority, 150, Student.MAX_GPA);
        fill("VeryHigh", veryHighPriority, 125, Student.MAX_GPA);
        fill("high", highPriority, 100, 3.5);
        fill("medium", mediumPriority, 75, 3.0);
        fill("low", lowPriority, 50, 2.0);
        fill("veryLow", veryLowPriority, 25, 1.0);
        fill("zero", zeroPriority, 0, 0.0);
        fillDuplicates(duplicates);

        CommandProcessor commandProcessor = new CommandProcessor();
        AddCommand addCommand = new AddCommand(jimPQ, mediumPriority[0]);
        commandProcessor.queueCommand(addCommand);
        commandProcessor.doIt();


        PollCommand pollCommand = new PollCommand(jimPQ);
        jimPQ.add(lowPriority[0]);
        CommandResult result = commandProcessor.doIt(pollCommand);
        Student student = (Student)result.getResult();
        System.out.println(student);

        for (int i = 0; i < 3; i++) {
            /*
            priorityQueue.add(mediumPriority[i]);
            priorityQueue.add(lowPriority[i]);
            priorityQueue.add(maxPriority[i]);
            priorityQueue.add(zeroPriority[i]);
            priorityQueue.add(veryHighPriority[i]);
            priorityQueue.add(veryLowPriority[i]);
            */
            addCommand = new AddCommand(jimPQ, mediumPriority[i]);
            commandProcessor.doIt(addCommand);
            addCommand = new AddCommand(jimPQ, lowPriority[i]);
            commandProcessor.doIt(addCommand);
            addCommand = new AddCommand(jimPQ, maxPriority[i]);
            commandProcessor.doIt(addCommand);
            addCommand = new AddCommand(jimPQ, zeroPriority[i]);
            commandProcessor.doIt(addCommand);
            addCommand = new AddCommand(jimPQ, veryHighPriority[i]);
            commandProcessor.doIt(addCommand);

        }
        jimPQ = printOrdered(jimPQ);

        comparator = ((Student s1, Student s2) ->  (s1.units < s2.units ?
                -1 : 1));

        ReprioritizeCommand reprioritizeCommand = new ReprioritizeCommand(jimPQ,
                comparator);
        commandProcessor.doIt(reprioritizeCommand);

        jimPQ = printOrdered(jimPQ);

        commandProcessor.undoIt();
        jimPQ = printOrdered(jimPQ);

        jimPQ.clear();
        System.out.println("clear");

        addCommand = new AddCommand(jimPQ, veryHighPriority[0]);
        CommandResult res = commandProcessor.doIt(addCommand);
        addCommand = new AddCommand(jimPQ, highPriority[0]);
        res = commandProcessor.doIt(addCommand);
        addCommand = new AddCommand(jimPQ, mediumPriority[0]);
        res = commandProcessor.doIt(addCommand);
        System.out.println(res.getResult());


        System.out.println(jimPQ);
        res = commandProcessor.undoIt();
        System.out.println(res.getResult());
        System.out.println(jimPQ);


        //AddItemCommand addItem = new AddItemCommand(jimPQ, highPriority[0]);
        //commandProcessor.addCommand(addItem);
        //commandProcessor.doIt(addItem);
        //PluggableCommand pc = new PluggableCommand(()->jimPQ.add(highPriority[1]),
        //        ()->jimPQ.remove(highPriority[1]));
        /*
        BiFunction<jsf.PriorityQueue, Student, Boolean> addStudent =
                (jsf.PriorityQueue pq, Student s) -> (pq.add(s));
        BiFunction<jsf.PriorityQueue, Student, Boolean> removeStudent =
                (jsf.PriorityQueue pq, Student s) -> (pq.remove(s));

        PluggableCommand pc = new PluggableCommand(addStudent,
                removeStudent, jimPQ,
                highPriority[1]);

        System.out.println("BEFORE");
        printOrdered(jimPQ);
        pc.execute();
        System.out.println("AFTER");

        //pc = makePC()

        //commandProcessor.addCommand(pc);
        //commandProcessor.doIt(pc);
        printOrdered(jimPQ);


        /*
        AddItemCommand addItem = new AddItemCommand(jimPQ);

        addItem.execute(lowPriority[1]);
        addItem.execute(highPriority[1]);
        printOrdered(jimPQ);
        addItem.undo();
        System.out.println();
        printOrdered(jimPQ);
        */


        /*
        PluggableCommand addItem =
                new PluggableCommand(()-> jimPQ.add(highPriority[1]),
                        ()->jimPQ.remove(highPriority[1]));


        PluggableCommand pollItem = new PluggableCommand(()->jimPQ.poll(),
                ()->jimPQ.poll());

        addItem.execute();
        //addItem.execute(lowPriority[1]);
        pollItem.execute();
        */

        /*
        iterator = jimPQ.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        */
        //printOrdered(jimPQ);

    }

    public static jsf.PriorityQueue printOrdered(jsf.PriorityQueue pq){
        System.out.println("\n*****BEGIN PRINT ORDERED ******");
        jsf.PriorityQueue backup = new jsf.PriorityQueue(pq.comparator);
        while (true){
            Student s = (Student)pq.poll();
            if (s == null)
                break;
            System.out.println(s);
            backup.add(s);
        }
        return backup;
    }

    // Make some fake students
    public static void fill(String namePrefix, Student[] s, int units, double gpa){
        for (int i = 0; i < s.length; i++){
            String name = namePrefix + "_" + i + "_" + gpa;
            s[i] = new Student(name, (int)(gpa*Math.pow(10,2) + i), name + ".com",
                    units, Math.max(gpa - 0.1*i,0));
        }
    }

    // make students with duplicate priority
    public static void fillDuplicates(Student[] duplicates){
        for (int i = 0; i < duplicates.length; i++){
            duplicates[i] = new Student("duplicate_" + i, 123,
                    "duplicate + i", 4, 4.0);
        }
    }

    // try making students whose GPA/Units are out of range
    public static void tryIllegalArgumentStudents(PriorityQueue priorityQueue){
        try{
            priorityQueue.add(new Student("lkj", 135, "email",
                    Student.MIN_UNITS-1, 3.0));
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }
        try{
            priorityQueue.add(new Student("mnb", 246, "email",
                    2, Student.MAX_GPA+0.1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }
        try{
            priorityQueue.add(new Student("mnb", 246, "email",
                    Student.MAX_UNITS+1, 4.0));
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }
        try{
            priorityQueue.add(new Student("mnb", 246, "email",
                    2, Student.MIN_GPA-0.1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }
    }


}
