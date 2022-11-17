/*
 * Author: Josh Knox
 * Date: 11/12/22
 * Description: Creates a database of residence whose backflow systems need to be tested
 * 				for compliance and manually loads information about residences in an array
 * 				of 100 entries.
 */
import javax.swing.JOptionPane;

public class HW3_Knox
{
	//declarations:
		//backflow:
		static Backflow [] backflow = new Backflow[100];
		//working variables:
		static String input = " ";
		static int start = 0;
		static int answer = JOptionPane.YES_OPTION;
		
	public static void main(String[] args)
	{
		//local working variables:
		int option = 0;
		String searchSerialNumber = " ";
		int foundAt = -1; //-1 means not found and any other number means found at that number
		int BFCount = 0;
		
		//prompt the user if they want to start the program or quit early
		start = JOptionPane.showConfirmDialog(null, "This program will determine the status of your backflow system" + "\n" +
													"Would you like to proceed?",
													"START", JOptionPane.YES_NO_OPTION);
		if(start == JOptionPane.NO_OPTION)
			JOptionPane.showMessageDialog(null, "Thank you for visiting this program. Goodbye!");
		else
		{
			//start loop:
			while (BFCount < 100)
			{
				//call menu options:
				option = menu();
				//Act on each option using a switch:
				switch(option)
				{
					case 1: //create object
							backflow[BFCount] = new Backflow();
							//get backflow
							backflow[BFCount].getBackflow();
							//count ++ for backflow: assume it is called BFCount
							BFCount++;
							break;
					case 2: //start a for loop and call to display all existing backflow
							for(int i = 0; i < BFCount; i++)
							{
								backflow[i].dispBackflow();;
							}
							break;
					case 3: //prompt user to enter a serial number to search
							input = JOptionPane.showInputDialog("Enter a serial number to search");
							//call a search method that returns the position of the search
							int i = searchBackflow(input, BFCount);
							//if found, display all attributes for this object at the position of the search
							//otherwise: not found
							if(i != -1)
								backflow[i].dispBackflow();
							else
							{
								JOptionPane.showMessageDialog(null, "The backflow was not found");
							}
							break;
					case 4: //thank you for using this program
							JOptionPane.showMessageDialog(null, "Thank you for visiting this program. Goodbye!");
							return;
					default: //error
							JOptionPane.showMessageDialog(null, "Error, please input an option 1-4");
				}//end switch
			}//end while BFCount < 100
			JOptionPane.showMessageDialog(null, "Thanks and goodbye!");
		}//end if for the start	
	}//end main
	//========================= Methods inside main() class that will act on all objects ====================
	public static int menu()
	{
		int option = 0;
		//prompt the user with the following options:
			/*
			 * -1 -Add backflow
			 * -2 -Display all existing backflow
			 * -3 -Search for a backflow by serial number
			 * -4 -Quit program
			 */
		input = JOptionPane.showInputDialog("1: Add a Backflow" + "\n" +
											 "2: Display all backflow systems" + "\n" +
											 "3: Search for a backflow" + "\n" +
											 "4: Quit program");
		option = Integer.parseInt(input);
		return option;
	}//end menu
	//==========================================================
	public static int searchBackflow(String searchSerialNumber, int BFCount)
	{
		for(int i = 0; i < BFCount; i++)
		{
			if(searchSerialNumber.equals(backflow[i].serialNumber))
				return i;
		}//end for i
		return -1;
	}//end searchBackflow
}//end class HW3_Knox
//==============================================================
class Backflow
{
	//attributes:
		//object Backflow
		String serialNumber;
		String make;
		String model;
		String address;
		String zip;
		int pressureLoss;
		boolean status;
		//working variables:
		String input = " ";
	//Constructors
		//constructor without arguments
		Backflow()
		{
			serialNumber = " ";
			make = " ";
			model = " ";
			address = " ";
			zip = " ";
			pressureLoss = 0;
			status = true;
		}//end const w/o arguments
		
		//constructor with arguments
		Backflow(String serialNumber, String make, String model, String address, String zip, int pressureLoss)
		{
			this.serialNumber = serialNumber;
			this.make = make;
			this.model = model;
			this.address = address;
			this.zip = zip;
			this.pressureLoss = pressureLoss;
			status = true;
		}//end const w/ arguments
	//========================= Methods ====================
		//get backflow data
		void getBackflow()
		{
			//get serial number
			input = JOptionPane.showInputDialog("Enter backflow serial number: ");
			serialNumber = input;
			System.out.println(serialNumber);
			//get make
			input = JOptionPane.showInputDialog("Enter backflow make: ");
			make = input;
			System.out.println(make);
			//get model
			input = JOptionPane.showInputDialog("Enter backflow model: ");
			model = input;
			System.out.println(model);
			//get address
			input = JOptionPane.showInputDialog("Enter address: ");
			address = input;
			System.out.println(address);
			//get zip
			input = JOptionPane.showInputDialog("Enter zip: ");
			zip = input;
			System.out.println(zip);
			//get pressure loss
			input = JOptionPane.showInputDialog("Enter backflow pressure loss(psi): ");
			pressureLoss = Integer.parseInt(input);
			System.out.println(pressureLoss);
		}//end getBackflow
		//determine status based on pressure loss
		boolean getStatus()
		{
			if(pressureLoss > 10)
				return true;
			else
			return false;
		}//end getStatus
		//display backflow report including all info plus a statement about the back flow based on status
		void dispBackflow()
		{
			JOptionPane.showMessageDialog(null, "Backflow Serial Number: " + serialNumber + "\n" +
												"Backflow Make: " + make + "\n" +
												"Backflow Model: " + model + "\n" +
												"Backflow Address: " + address + "\n" +
												"Backflow ZipCode: " + zip + "\n" +
												"Backflow Pressure Loss: " + pressureLoss + "\n" +
												"Backflow Status: " + (getStatus() ? "System needs to be repaired" : "System needs further testing"));
		}//end dispBackflow
}//end class Backflow