//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public abstract class Main {
    private String name;
    private int age;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
    //Example 1: Class Documentation
    /**
     * <p>
     * Represents a simple calculator that can perform basic arithmetic operations.
     * </p>
     *
     * <p>Example usage:</p>
     * <blockquote><pre>
     *     Calculator calc = new Calculator();
     *     int sum = calc.add(5, 3);
     * </pre></blockquote>
     */
    public class Calculator {

        // Class implementation...
    }
    //Example 2: Method Documentation with Parameters and Return Value
    /**
     * <p>
     * Adds two integers and returns the result.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     int result = calculator.add(2, 3);
     *     // result is 5
     * </pre></blockquote>
     *
     * @param a the first integer to add
     * @param b the second integer to add
     * @return the sum of {@code a} and {@code b}
     */
    public int add(int a, int b) {
        return a + b;
    }
    //Example 3: Method Documentation with Exception
    /**
     * <p>
     * Divides one number by another.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     double result = calculator.divide(10, 2);
     *     // result is 5.0
     * </pre></blockquote>
     *
     * @param numerator   the number to be divided
     * @param denominator the number by which to divide
     * @return the result of the division
     * @throws ArithmeticException if {@code denominator} is zero
     */
    public double divide(double numerator, double denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return numerator / denominator;
    }
    //Example 4: Constructor Documentation
    /**
     * <p>
     * Constructs a new instance of {@code Person} with the given name and age.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     Person person = new Person("Alice", 30);
     * </pre></blockquote>
     *
     * @param name the name of the person
     * @param age  the age of the person
     */
    public void Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //Example 5: Method Documentation with {@code} and {@link}
    /**
     * <p>
     * Converts a string to an integer.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     int number = Utils.parseInt("42");
     *     // number is 42
     * </pre></blockquote>
     *
     * @param str the string to convert
     * @return the integer value of the string
     * @throws NumberFormatException if the string cannot be parsed as an integer
     * @see Integer#parseInt(String)
     */
    public static int parseInt(String str) {
        return Integer.parseInt(str);
    }
    //Example 6: Method Documentation with List Parameter
    /**
     * <p>
     * Processes a list of orders.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     List&lt;Order&gt; orders = Arrays.asList(order1, order2);
     *     orderProcessor.processOrders(orders);
     * </pre></blockquote>
     *
     * @param orders the list of orders to process
     */
    public void processOrders(int orders) {
        // Method implementation...
    }
    //Example 7: Enum Documentation
    /**
     * <p>
     * Represents the days of the week.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     Day day = Day.MONDAY;
     * </pre></blockquote>
     */
    public enum Day {
        SUNDAY,
        MONDAY,
        TUESDAY,
        // Other days...
    }
    //Example 8: Field Documentation
    /**
     * <p>
     * The maximum number of connections allowed.
     * </p>
     */
    private static final int MAX_CONNECTIONS = 100;
    //Example 9: Deprecated Method
    /**
     * <p>
     * Calculates the square root of a number.
     * </p>
     *
     * @param number the number to calculate the square root of
     * @return the square root of the number
     * @deprecated Use {@link Math#sqrt(double)} instead.
     */
    @Deprecated
    public double squareRoot(double number) {
        return Math.sqrt(number);
    }
    //Example 10: Interface Documentation
    /**
     * <p>
     * Defines the contract for a shape that can calculate its area.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     Shape shape = new Circle(5);
     *     double area = shape.calculateArea();
     * </pre></blockquote>
     */
    public interface Shape {

        /**
         * Calculates the area of the shape.
         *
         * @return the area of the shape
         */
        double calculateArea();
    }
    //Example 11: Generic Class Documentation
    /**
     * <p>
     * A container that can hold objects of any type.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     Container&lt;String&gt; container = new Container&lt;&gt;();
     *     container.set("Hello");
     *     String value = container.get();
     * </pre></blockquote>
     *
     * @param <T> the type of the value
     */
    public class Container<T> {

        private T value;

        /**
         * Sets the value of this container.
         *
         * @param value the value to set
         */
        public void set(T value) {
            this.value = value;
        }

        /**
         * Gets the value of this container.
         *
         * @return the current value
         */
        public T get() {
            return value;
        }
    }
    //Example 12: Method Documentation with Multiple Exceptions
    /**
     * <p>
     * Reads data from a file.
     * </p>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     String data = fileReader.readFile("data.txt");
     * </pre></blockquote>
     *
     * @param filePath the path to the file
     * @return the contents of the file as a string
     * @throws IOException      if an I/O error occurs
     * @throws FileNotFoundException if the file does not exist
     */
    public void readFile(String filePath) throws IOException, FileNotFoundException {
        // Method implementation...
    }
    //Example 13: Method Documentation with Formatting Tags
    /**
     * <p>
     * Sends an email message.
     * </p>
     *
     * <p>Usage:</p>
     * <ul>
     *     <li>Ensure SMTP settings are configured.</li>
     *     <li>Call {@code sendEmail} with recipient and message.</li>
     * </ul>
     *
     * <p>Example:</p>
     * <blockquote><pre>
     *     emailService.sendEmail("user@example.com", "Hello World");
     * </pre></blockquote>
     *
     * @param recipient the email address of the recipient
     * @param message   the message to send
     */
    public void sendEmail(String recipient, String message) {
        // Method implementation...
    }








//TIP To rename this variable, place the caret on it and press <shortcut actionId="RenameElement"/>.
//This will safely rename all occurrences.
    int myVariable = 42;

    //TIP Use <shortcut actionId="GotoDeclaration"/> to navigate to the declaration of `processData`.
    abstract void sendEmail();

    //TIP Press <shortcut actionId="ReformatCode"/> to format your code according to the project's style guidelines.
    public void   unformattedMethod () {   System.out.println("Needs formatting!"); }

    //TIP Click the gutter or press <shortcut actionId="ToggleLineBreakpoint"/> to add a breakpoint here before debugging.
    int result = complexCalculation();
    protected abstract int complexCalculation();
    //TIP If you see a warning or error, press <shortcut actionId="ShowIntentionActions"/> to view available quick fixes.
//    List<String> list = new ArrayList<>();
//    list.add("Item");
    //TIP Start typing and press <shortcut actionId="CodeCompletion"/> to see code completion suggestions.
    String message = "Hello";
    //TIP You can quickly comment or uncomment code by selecting it and pressing <shortcut actionId="CommentByLineComment"/>.
//    System.out.println("This can be commented out.");
    //TIP Place the caret inside the class and press <shortcut actionId="Generate"/> to generate getters and setters.
    public class User {
        private String name;
        private int age;
        // Getters and setters can be generated here.
    }
        //TIP Use <shortcut actionId="Switcher"/> to quickly switch between open files and tool windows.
        //Press <shortcut actionId="RecentFiles"/> to view and open recently accessed files.
    // TIP Duplicate this line by pressing <shortcut actionId="EditorDuplicate"/>.
// System.out.println("Duplicate me!");

    //TIP You can collapse this method by clicking the minus sign in the gutter or pressing <shortcut actionId="CollapseRegion"/>.
    public void longMethod() {
        // Many lines of code...
    }


    private class IOException extends Exception {
    }

    private class FileNotFoundException extends Exception {
    }
}

