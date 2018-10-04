# CommandPattern-PriorityQueue
Priority Queue using Command Pattern, Strategy Pattern, and Iterators
Objective:
- Recreate Java's Priority Queue in its proper place within Java's Collection Heirarchy
- Implement the Command Pattern to allow Undo
- Implement the Strategy Pattern to allow custom prioritization on creation
- JUnit testing of all features, and testing the test cases
- See Assignment2.pdf for additional information

How the Command Pattern works:
- An abstract Command Class is used to ensure all Commands have an execute() and undo() function
- A ConcreteCommand is then created for each command that modifies the PriorityQueue -- AddCommand, RemoveCommand, PollCommand, etc
- The CommandProcessor is used to track Commands sent from the client to the PriorityQueue
- Commands may be added to a commandStack to be executed at a later time, or executed immediately. The CommandProcessor calls the Command's execute() function
- Upon executon the Command is added to the undoStack.  An Undo request initiates the CommandProcessor to pop the last executed Commmand from the undoStack and call its undo() function.
- Adding the Redo feature simply requires an additional redoStack that contains all the completed undo commands - executing redo() simply calls that Command's execute() function again.

Challenge - Uncertain Return Typing:
- Java's Priority Queue uses two return types - a boolean for add(T t)/remove(T t), and the item stored for poll()
- To allow each return type: the ConcreteCommands, the CommandProcessor, and the Client would all need to know the return type and which method to call
  - executing an add(T t) would need to be something like `boolean executeBoolean(T t)` 
  - the CommandProcessor would then need an `boolean executeBoolean(Command command){command.executeBoolean();}`
  - and the Client would need to know to call that instead of a simple execute
- execute() can clearly get out of hand fast, and undo would be even worse - the Client would need to remember track each Command's return type to call the appropriate CommandProcessor undo function
- Instead, the CommandResult Class is used to wrap each result as a simple Object
  - The constructor can receive either return type, and easily add additional types
  - The Commands and CommandProcessor now simply have the execute()/undo() methods, and always return CommandResult
  - it is the Clients responsibility to then unwrap the CommandResult to see their appropriate return value
