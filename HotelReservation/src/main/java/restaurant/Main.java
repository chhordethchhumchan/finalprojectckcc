package restaurant;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		HashMap<String, Tables> tableList = new HashMap<String, Tables>();
		HashMap<String, Food> foodList = new HashMap<String, Food>();
		ArrayList<Order> orderList = new ArrayList<Order>();
		Reservation reservation = new Reservation();
		
		String menuNumber = "";
		do {
			// Main menus of program
			System.out.println("---- Program Menus ----------");
			System.out.println("| 1. New Table      		|");
			System.out.println("| 2. List of Tables 		|");
			System.out.println("| 3. New Food       		|");
			System.out.println("| 4. List of Foods  		|");
			System.out.println("| 5. Take Reservation 		|");
			System.out.println("| 6. List of Reservation 	|");
			System.out.println("| 7. Take Order          	|");
			System.out.println("| 8. List of Order      	|");
			System.out.println("-----------------------------");
			int mainSelectedMenu = sc.nextInt();
			sc.nextLine();
			switch(mainSelectedMenu) {
				// New Table
				case 1 :
					String answer = "";
					do {
						System.out.println("----------------- Create New Table --------------------\n");
						System.out.println("Enter your table No: ");
						String tableNo = sc.nextLine();
						System.out.println("Enter number of seats: ");
						int numOfSeat = sc.nextInt();
						sc.nextLine();
						// Create object of table
						Tables tb = new Tables(numOfSeat, tableNo, true);
						tableList.put(tableNo, tb);
						System.out.println("---------------------------------------------------");
						System.out.println("Do you want to add more table? (Y/N)");
						answer = sc.nextLine();
					}while(answer.equalsIgnoreCase("Y"));
					break;
				// List of Tables
				case 2 :
					System.out.println("----------------- Table List --------------------\n");
					System.out.println("[No              NumberOfSeats              Status]");
					System.out.println("---------------------------------------------------");
					for(Tables tb : tableList.values()){
						System.out.println(tb.toString());
					}
					System.out.println("---------------------------------------------------");
					break;
				// New Food
				case 3 :
					String answerFood = "";
					do {
						System.out.println("----------------- Create New Food Menu --------------------\n");
						System.out.println("Enter your food code: ");
						String foodCode = sc.nextLine();
						System.out.println("Enter your food name: ");
						String foodName = sc.nextLine();
						System.out.println("Enter your food price: ");
						double foodPrice = sc.nextDouble();
						sc.nextLine();
						// Create object of food
						Food fd = new Food(foodCode, foodName, foodPrice);
						foodList.put(foodCode, fd);
						System.out.println("---------------------------------------------------");
						System.out.println("Do you want to add more food? (Y/N)");
						answerFood = sc.nextLine();
					}while(answerFood.equalsIgnoreCase("Y"));
					break;
				// List of Foods
				case 4 :
					System.out.println("----------------- Food List --------------------\n");
					System.out.println("[Code        Name                           Price]");
					System.out.println("---------------------------------------------------");
					for(Food fd : foodList.values()){
						System.out.println(fd.toString());
					}
					System.out.println("---------------------------------------------------");
					break;
				//Take Reservation
				case 5:
					String answerBooking = "";
					int bookNo = 1;
					do {
						System.out.println("----------------- Take Reservation --------------------\n");
						System.out.println("Enter customer name: ");
						String cusName = sc.nextLine();
						System.out.println("Enter customer contactNo: ");
						String cusContact = sc.nextLine();
						//Create booking object
						Booking newBooking = new Booking(bookNo,cusName,cusContact);
						System.out.println("How many tables you want to book? : ");
						int numT = sc.nextInt();
						sc.nextLine();
						for(int i=1; i<= numT; i++) {
							System.out.println("Enter table number you want to reserve: ");
							String tNo = sc.nextLine();
							if(tableList.containsKey(tNo)) {
								Tables reservedTable = tableList.get(tNo);
								if(reservedTable.getOccupied()) {
									//Set reservation to table
									//newBooking.setReservedTable(reservedTable);
								}else {
									System.out.println("Table with number " + tNo + " is busy.\n");
								}
							}else {
								System.out.println("Table with number " + tNo + " is not found in list.\n");
							}
						}
						//Add to reservation list
						reservation.create(newBooking);
						bookNo++;
						System.out.println("---------------------------------------------------");
						System.out.println("Do you want to take more reservation? (Y/N)");
						answerBooking = sc.nextLine();
					}while(answerBooking.equalsIgnoreCase("Y"));
					break;
				//List of Reservation
				case 6:
					System.out.println("----------------- List of Reservation --------------------\n");
					System.out.println("[No    CustomerName            Contact               Tables]");
					System.out.println("------------------------------------------------------------");
					System.out.println(reservation.toString());
					System.out.println("------------------------------------------------------------");
					break;
				//Take Order
				case 7:
					String answerOrder = "";
					int orderNo = 1;
					do {
						System.out.println("----------------- Take Reservation --------------------\n");
						System.out.println("Enter table number you want to order: ");
						String tNo = sc.nextLine();
						if(tableList.containsKey(tNo)) {
							Tables orderedTable = tableList.get(tNo);
							//create object order
							Order newOrder = new Order(orderNo, orderedTable);
							if(orderedTable.getOccupied()) {
								System.out.println("How many foods you want to order? : ");
								int numF = sc.nextInt();
								sc.nextLine();
								for(int i=1; i<= numF; i++) {
									System.out.println("Enter food code you want to eat: ");
									String fCode = sc.nextLine();
									if(foodList.containsKey(fCode)) {
										Food orderedFood = foodList.get(fCode);
										newOrder.create(orderedFood);
									}else {
										System.out.println("Food with code " + fCode + " is not found in list.\n");
									}
								}
								orderList.add(newOrder);
								orderedTable.setOccupied(false);
								orderNo++;
							}else {
								System.out.println("Table with number " + tNo + " is busy.\n");
							}
						}else {
							System.out.println("Table with number " + tNo + " is not found in list.\n");
						}
						System.out.println("---------------------------------------------------");
						System.out.println("Do you want to take more order? (Y/N)");
						answerOrder = sc.nextLine();
					}while(answerOrder.equalsIgnoreCase("Y"));
					break;
				//List of Order
				case 8:
					System.out.println("----------------- List of Order --------------------\n");
					for(Order od : orderList){
						System.out.println(od.toString());
					}
					System.out.println("----------------------------------------------------");
					break;
			}
			System.out.println("Do you want to continue to Main Menu? (Y/N)");
			menuNumber = sc.next();
		}while(menuNumber.equalsIgnoreCase("Y"));
	}
	
}
