/*
 * Program: Peel Explorer
 * Created By: Sheza Khan & Aliza Azam
 * Date: Dec 14, 2023 - Dec 22, 2023
 *
 * Description: This Java program helps users plan a day in Mississauga by providing recommendations
 * for coffee shops, shopping, lunch, arcade, salon, dinner, and dessert. 
 * It interacts with the 2022 Mississauga business directory data set to suggest options based on user preferences 
 * and generates a final itinerary displayed in with graphical user interface.
 * 
 * Tasks Sheza Completed: 
 * - Methods shopping, lunch, and amusement arcades. 
 * - Implementing a way to print 3 different options for the user
 * - Creating the final choices array which helped Aliza print the final itinerary
 *  
 * Tasks Aliza Completed: 
 * - Methods salon, dinner, dessert
 * - Implementing a way to find the address in the data set of the chosen attraction by the user
 * - Creating final itinerary method to display the final itinerary on the screen 
 * - Implementing GUI to display the final itinerary
 * 
 * Tasks Completed Together: 
 * - Coffee method completed together
 * 
 * Business method completed with Mrs. Kupresak's help!
 */

import java.util.Scanner;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.*;
import javax.swing.*;

class Main {

  JFrame frame;
  JPanel contentPane;

  // Start Scanner
  static Scanner input = new Scanner(System.in);

  // Declare global variables
  static String[][] business_array = new String[15166][24];
  static int row = 0;
  static String[] finalChoices = new String[8];
  static String[] finalAddresses = new String[8];

  /**
   * Reads business data from a file and populates a 2D array with the
   * information.
   * Precondition: None
   * Postcondition: The business_array 2D array is populated with data from the
   * specified file.
   */
  public static void business(String fileName) {

    // Declare input file
    File inputFile = new File(fileName);

    // Declare variables
    String lineOfText;

    try {
      FileReader in = new FileReader(inputFile);
      BufferedReader readFile = new BufferedReader(in);

      while ((lineOfText = readFile.readLine()) != null) {
        String[] colInfo = lineOfText.split(",");

        for (int c = 0; c < 24; c++) {
          business_array[row][c] = colInfo[c];
        }
        row++;
      }
      readFile.close();
      in.close();

      System.out.println();

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      System.err.println("FileNotFoundException: " + e.getMessage());
    }

    catch (IOException e) {
      System.out.println("IO Error");
      System.err.println("IOException: " + e.getMessage());
    }
  }

  /**
   * Provides recommendations for coffee shops based on user preferences and saves
   * the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays coffee shops, user picks one, and
   * choice/address are saved.
   * - If chosen coffee shop not found, prints "Restaurant not found."
   * - The user's choice of a coffee shop is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void coffee() {
    // Declare variables
    String userInput;
    String[] coffeeChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print("\n\tGood Morning! Would you like to start your day with a visit to a local coffee shop?: ");
    userInput = input.nextLine();
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {
      if (userInput.equalsIgnoreCase("yes")) {
        String coffeeshop = business_array[i][4];

        if (coffeeshop != null && coffeeshop.contains(("Coffee"))) {
          String limitedeating = business_array[i][8];

          if (limitedeating.contains(("Limited-service eating"))) {
            counter += 1;
            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);

              coffeeChoice[counter] = business_array[i][4];
            }
          }

        }
      }

    }

    System.out.print("\n\tFrom the choices above where would you like to have coffee?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[0] = coffeeChoice[pick];

    // Find the row where the cafe is located
    int row = -1;
    for (int i = 0; i < business_array.length && row == -1; i++) {
      if (business_array[i][4].equals(coffeeChoice[pick])) {
        row = i;
      }
    }

    if (row != -1) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String coffeeAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[0] = coffeeAddress;

      System.out.println("\n\tAwesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }

  }

  /**
   * Provides recommendations for shopping places based on user preferences and
   * saves the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays shopping places, user picks one, and
   * choice/address are saved.
   * - If chosen shopping place not found, prints "Restaurant not found."
   * - The user's choice of a shopping place is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void shopping() {
    // Declare variables
    String userInput;
    String[] shoppingChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print("\n\tLet's score some deals and explore Mississauga's shops! Would you like to go shopping?: ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 1005; i < business_array.length; i++) {
      if (userInput.equalsIgnoreCase("yes")) {

        String allRetailStore = business_array[i][4];

        if (allRetailStore != null) {
          String storeChoice = business_array[i][8];

          if (storeChoice.contains(("Family Clothing Stores"))) {
            counter += 1;
            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);
              shoppingChoice[counter] = business_array[i][4];

            }
          }
        }
      }
    }

    System.out.print("\n\tFrom the choices above where would you like to have shopping?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[1] = shoppingChoice[pick];

    // Find the row where the store is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(shoppingChoice[pick])) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String storeAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[1] = storeAddress;

      System.out.println("\n\tAwesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }

  }

  /**
   * Provides recommendations for lunch places based on user preferences and saves
   * the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays lunch places, user picks one, and
   * choice/address are saved.
   * - If chosen lunch place not found, prints "Restaurant not found."
   * - The user's choice of a lunch place is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void lunch() {
    // Declare variables
    String userInput;
    String[] restaurantChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print(
        "\n\tIt's noon, you might be hungry by now! Would you like to go a fast-food restaurant to have some lunch?: ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {
      if (userInput.equalsIgnoreCase("yes")) {

        String allLunchPlaces = business_array[i][4];

        if (allLunchPlaces != null) {
          String lunchChoice = business_array[i][8];

          if (lunchChoice.contains(("Limited-service eating places"))) {
            counter += 1;

            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);

              // Declare new string array to save the user's choice restaurant
              restaurantChoice[counter] = business_array[i][4];
            }
          }
        }
      }
    }

    System.out.print("\n\tFrom the choices above where would you like to have lunch?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[2] = restaurantChoice[pick];

    // Find the row where the restaurant is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(restaurantChoice[pick])) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String lunchAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[2] = lunchAddress;

      System.out.println("\n\tAwesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }

  }

  /**
   * Provides recommendations for amusement arcades based on user preferences and
   * saves the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays arcades, user picks one, and
   * choice/address are saved.
   * - If chosen arcade not found, prints "Restaurant not found."
   * - The user's choice of an amusement arcade is stored in the finalChoices
   * array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void amusementArcades() {
    // Declare variables
    String userInput;
    String[] amusementChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print("\n\tHmm, what next? I think it's time for some fun! - Would you like to head to an arcade?: ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {
      if (userInput.equalsIgnoreCase("yes")) {

        String allArcades = business_array[i][4];

        if (allArcades != null) {
          String arcade = business_array[i][8];

          if (arcade.contains(("Amusement Arcades"))) {
            counter += 1;

            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);

              // Declare new string array to save the user's choice restaurant
              amusementChoice[counter] = business_array[i][4];
            }
          }
        }
      }
    }

    System.out.print("\n\tFrom the choices above where would you like to go for arcade?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[2] = amusementChoice[pick];

    // Find the row where the arcade is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(amusementChoice[pick])) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String amusementAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[3] = amusementAddress;

      System.out.println("\n\tAwesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }

  }

  /**
   * Provides recommendations for salons based on user preferences and saves the
   * user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays salons, user picks one, and choice/address
   * are saved.
   * - If chosen salon not found, prints "Restaurant not found."
   * - The user's choice of a salon is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void salon() {
    // Declare variables
    String userInput;
    String[] salonChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print("\n\tIt's been a tiring day! Would you like to relax at one of Mississauga's finest salons?: ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {

      if (userInput.equalsIgnoreCase("yes")) {
        String allSalons = business_array[i][4];

        if (allSalons != null) {
          String beautySalon = business_array[i][8];

          if (beautySalon.contains(("Beauty Salons"))) {
            counter += 1;

            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);

              // Declare new string array to save the user's choice salon
              salonChoice[counter] = business_array[i][4];
            }
          }
        }
      }
    }
    System.out.print("\n\tFrom the choices above where would you like to relax?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[4] = salonChoice[pick];

    // Find the row where the salon is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(salonChoice[pick])) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String salonAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[4] = salonAddress;

      System.out.println("\n\tAwesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }
  }

  /**
   * Provides recommendations for dinner places based on user preferences and
   * saves the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user selects a cuisine, displays restaurants, user picks one, and
   * choice/address are saved.
   * - If chosen restaurant not found, prints "Restaurant not found."
   * - The user's choice of a dinner place is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void dinner() {
    // Declare variables
    String userInput;
    String[] dinnerRestaurantChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out
        .print("\n\tIt's time for dinner! Let's eat! Which of the following cuisines would you like to try today?");
    System.out.println("\n1. Chinese"
        + "\n2. Japanese"
        + "\n3. Italian");
    System.out.print("\n>> ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {

      // Option 1: Chinese
      if (userInput.equals("1")) {

        String allDinnerPlaces = business_array[i][4];

        if (allDinnerPlaces != null) {
          String dinnerChoice = business_array[i][4];

          if (dinnerChoice.contains("Chinese")) {
            String fullServiceRestaurant = business_array[i][8];

            if (fullServiceRestaurant.contains("Full-service restaurants")) {
              counter += 1;

              if (counter <= 3) {
                System.out.println(counter + ". " + business_array[i][4]);

                // Declare new string array to save the user's choice restaurant
                dinnerRestaurantChoice[counter] = business_array[i][4];
              }
            }
          }
        }
      }

      // Option 2: Japanese
      if (userInput.equals("2")) {

        String allDinnerPlaces = business_array[i][4];

        if (allDinnerPlaces != null) {
          String dinnerChoice = business_array[i][4];

          if (dinnerChoice.contains("Japanese")) {
            String fullServiceRestaurant = business_array[i][8];

            if (fullServiceRestaurant.contains("Full-service restaurants")) {
              counter += 1;

              if (counter <= 3) {
                System.out.println(counter + ". " + business_array[i][4]);

                // Declare new string array to save the user's choice restaurant
                dinnerRestaurantChoice[counter] = business_array[i][4];
              }
            }
          }
        }
      }
      // Option 3: Italian
      if (userInput.equals("3")) {

        String allDinnerPlaces = business_array[i][4];

        if (allDinnerPlaces != null) {
          String dinnerChoice = business_array[i][4];

          if (dinnerChoice.contains("Italian")) {
            String fullServiceRestaurant = business_array[i][8];

            if (fullServiceRestaurant.contains("Full-service restaurants")) {
              counter += 1;

              if (counter <= 3) {
                System.out.println(counter + ". " + business_array[i][4]);

                // Declare new string array to save the user's choice restaurant
                dinnerRestaurantChoice[counter] = business_array[i][4];
              }
            }
          }
        }
      }
    }

    System.out.print("\nFrom the choices above where would you like to have dinner?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[5] = dinnerRestaurantChoice[pick];

    // Find the row where the restaurant is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(dinnerRestaurantChoice[pick])) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String dinnerAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[5] = dinnerAddress;

      System.out.println("Awesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }
  }

  /**
   * Provides recommendations for dessert places based on user preferences and
   * saves the user's choice.
   * Precondition: None
   * Postcondition:
   * - If user declines ('no'), exits.
   * - If user agrees ('yes'), displays dessert places, user picks one, and
   * choice/address are saved.
   * - If chosen dessert place not found, prints "Restaurant not found."
   * - The user's choice of a dessert place is stored in the finalChoices array,
   * and the corresponding address is stored in the finalAddresses array.
   */
  public static void dessert() {
    // Declare variables
    String userInput;
    String[] dessertRestaurantChoice = new String[4];
    int counter = 0;

    // Prompt user
    System.out.print(
        "\nAre you craving anything sweet? Perhaps a delicious slice of cake, or a yummy, cold scoop of ice-cream?: ");
    userInput = input.nextLine();

    if (userInput.equalsIgnoreCase("no")) {
      System.out.println("Okay moving on!");
      return;
    }

    for (int i = 0; i < business_array.length; i++) {
      if (userInput.equalsIgnoreCase("yes")) {

        String allDessertPlaces = business_array[i][4];

        if (allDessertPlaces != null) {
          String dessertChoice = business_array[i][4];

          if (dessertChoice.contains("Sweet") || dessertChoice.contains("Le Bon Croissant")
              || dessertChoice.contains(("Baskin Robbins"))) {
            counter += 1;

            if (counter <= 3) {
              System.out.println(counter + ". " + business_array[i][4]);

              // Declare new string array to save the user's choice restaurant
              dessertRestaurantChoice[counter] = business_array[i][4];
            }
          }
        }
      }
    }
    System.out.print("\nFrom the choices above where would you like to have dessert?: ");
    int pick = input.nextInt();
    input.nextLine();

    finalChoices[6] = dessertRestaurantChoice[pick];

    String restaurantChoice = dessertRestaurantChoice[pick];

    // Find the row where the restaurant is located
    int row = 0;
    for (int i = 1; i < business_array.length && row == 0; i++) {
      if (business_array[i][4].equals(restaurantChoice)) {
        row = i;
      }
    }

    if (row != 0) {
      String streetNum = business_array[row][14];
      String streetName = business_array[row][15];
      String postalCode = business_array[row][16];

      String dessertAddress = streetNum + " " + streetName + ", " + postalCode;
      finalAddresses[6] = dessertAddress;

      System.out.println("Awesome, I've saved that!");
      System.out.println("______________________________________________");
    } else {
      System.out.println("Restaurant not found.");
    }
  }

  /**
   * Displays the final itinerary, including the choices and addresses of selected
   * places.
   * Precondition: None
   * Postcondition: The final itinerary, including choices and addresses, is
   * displayed.
   */
  public static void fullDayItinerary() {

    System.out.println();
    System.out.println("***** Your Final Itinerary is Here! *****");
    System.out.println();

    // Print final day plan
    for (int i = 0; i < finalChoices.length && i < finalAddresses.length; i++) {

      if (finalChoices[i] != null && finalAddresses[i] != null) {
        System.out.print(finalChoices[i]);
        System.out.print(" - ");
        System.out.print(finalAddresses[i]);
        System.out.println();
      }
    }

  }

  /**
   * Creates and displays the graphical user interface for the final itinerary.
   * Precondition: None
   * Postcondition: The graphical user interface is created and displayed for the
   * final itinerary.
   */
  public Main() {
    // Create and set up the frame
    frame = new JFrame("Final Itinerary");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a content pane
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // Title label
    JLabel title = new JLabel("***** Your Final Itinerary is Ready! *****");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("Calibri", Font.BOLD, 18));
    title.setForeground(new Color(128, 0, 128)); // Purple color
    contentPane.add(title);

    // Add final day plan labels
    for (int i = 0; i < finalChoices.length && i < finalAddresses.length; i++) {
      if (finalChoices[i] != null && finalAddresses[i] != null) {
        JLabel choiceLabel = new JLabel(finalChoices[i]);
        JLabel dashLabel = new JLabel(" - ");
        JLabel addressLabel = new JLabel(finalAddresses[i]);

        choiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set label font and color
        choiceLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        dashLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        addressLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        choiceLabel.setForeground(new Color(25, 25, 112)); // Blue color
        dashLabel.setForeground(new Color(128, 0, 128)); // Purple color
        addressLabel.setForeground(new Color(0, 0, 0)); // Black color

        contentPane.add(choiceLabel);
        contentPane.add(dashLabel);
        contentPane.add(addressLabel);
      }
    }

    // Background
    contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.setBackground(Color.white);

    // Add content pane to frame
    frame.setContentPane(contentPane);

    // Size and then display the frame
    frame.pack();
    frame.setLocationRelativeTo(null); // Center the frame on the screen
    frame.setVisible(true);
  }

  /**
   * Create and show the GUI.
   */
  private static void runGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    Main itinerary = new Main();
  }

  public static void main(String[] args) {

    int userInput; // Declare variable

    // Introduction
    System.out.println("\n\t\t\t\t\tWelcome to Peel Explorer!"
        + "\n\nChoose the city you would like to explore and plan for today!");
    System.out.println("\n1. Mississauga"
        + "\n2. Brampton (UNDER CONSTRUCTION)"
        + "\n3. Caledon (UNDER CONSTRUCTION)");

    // Prompt user for city choice
    do {
      System.out.print(">> ");
      userInput = input.nextInt();
    } while (userInput != 1);

    // Instructions
    System.out.println("\nAnswer each question with a 'Yes' or 'No' to plan your day!");

    business("Business_Directory.txt");

    coffee();
    shopping();
    lunch();
    amusementArcades();
    salon();
    dinner();
    dessert();
    fullDayItinerary();

    // Methods that create and shows GUI
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }

    });
  }
}