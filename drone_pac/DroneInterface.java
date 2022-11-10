package drone_pac;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Simple program to show arena with multiple drones
* @author shsmchlr
 *
 */
public class DroneInterface {
	
	private Scanner s;								// scanner used for input from user
    private DroneArena myArena;				// arena in which drones are shown
    /**
    	 * constructor for DroneInterface
    	 * sets up scanner used for input and the arena
    	 * then has main loop allowing user to enter commands
     */
    public DroneInterface() {
    	 s = new Scanner(System.in);			// set up scanner for user input
    	 myArena = new DroneArena(20, 6);	// create arena of size 20*6
    	
        char ch = ' ';
        do {
        	System.out.print("Enter (A)dd drone, get (I)nformation, show arena (D)isplay,(M)ove Drones, Enter (C)ustomise to change arena size or e(X)it > ");
        	ch = s.next().charAt(0);
        	s.nextLine();
        	switch (ch) {
    			case 'A' :
    			case 'a' :
        					myArena.addDrone();	// add a new drone to arena
        					break;
        		case 'I' :
        		case 'i' :
        					System.out.print(myArena.toString());
            				break;
        		case 'x' : 	ch = 'X';				// when X detected program ends
        					break;
				case 'd':
				case 'D':
					
							doDisplay();
							break;
				case 'c':
				case 'C':
							System.out.println("enter a height");
							int u_height = s.nextInt();
							System.out.println("enter a width");
							int u_width = s.nextInt();
							System.out.println("The arena hieght is set to " + u_height + " and width " + u_width);
							myArena = new DroneArena(u_height, u_width);
							
							break;
				case 'M':
				case 'm':
							for (int i = 0; i < 10; i++) {
								myArena.moveAllDrones(myArena);
								doDisplay();
								try {
									Thread.sleep(200);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}			


/* 							myArena.moveAllDrones(myArena);	
							doDisplay();
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} */
							break;
							
				case 's':
				case 'S':
							save();
							break;

				case 'l':
				case 'L':
							break;

        	}
    		} while (ch != 'X');						// test if end
        
       s.close();									// close scanner
    }

	void save(){
		System.out.println("Enter a file name: ");
		String file_name = s.nextLine();
		File file = new File(file_name+".txt");
		try {
			boolean result = file.createNewFile();
			if (result){
				System.out.println("file sucessfully created");
			}
			else{
				System.out.println("file already exists at location: "+file.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	void load(){

	}
    
	void doDisplay(){
		int x = DroneArena.get_x(myArena);
		int y = DroneArena.get_y(myArena);
		ConsoleCanvas Arena = new ConsoleCanvas(x, y);
		myArena.showDrones(Arena);
		System.out.println(Arena.toString());

	}
	public static void main(String[] args) {
		DroneInterface r = new DroneInterface();	// just call the interface
	}

}