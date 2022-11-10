package drone_pac;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import drone_pac.DroneArena;
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
			System.out.print("Enter (S)ave or Enter (L)oad ");
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
				case 'z':
				case 'Z':
							for (int i = 0; i < 10; i++) {
								myArena.moveAllDrones(myArena);
								doDisplay();
								try {
									Thread.sleep(200);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}			

							break;
				case 'm':
				case 'M':
							myArena.moveAllDrones(myArena);	
							doDisplay();
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
							load();
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
				write();
			}
			else{
				System.out.println("file already exists at location: "+file.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	void write(){
		try {
			System.out.println("Enter a file name to write");
			String file_name = s.nextLine();
			// FileOutputStream fos = new FileOutputStream(file_name+".txt", true);
			File file = new File(file_name + ".txt");
			FileWriter fw = new FileWriter(file);

			// write arena data.
			fw.write("arena," + myArena.get_x()+","+myArena.get_y()+"\n");
	

			for (Drone d : myArena.Drone_array) {
				fw.write("drone," +d.getDroneid()+","+d.getX()+","+d.getY()+","+d.getDirection()+"\n");
				// write drone data
			}
			
			//enter file conetent here
			//System.out.println("enter file content here");
          //writes bytes into file  
		  	fw.flush();
			fw.close();            //close the file  
			System.out.println("file Written.");  
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//FileReader and BufferedReader
	void load(){
		System.out.println("Enter a file name to Load");
		String file_name = s.nextLine();   
		try (BufferedReader br = new BufferedReader(new FileReader(file_name))){
			String line;
			while((line = br.readLine()) != null){
				String[] dl = line.split(",");
				if (dl[0]== "arena"){
					int x = Integer.parseInt(dl[1]);
					int y = Integer.parseInt(dl[2]);
					myArena.set_x(x);
					myArena.set_y(y);


				}
				else{
					int x = Integer.parseInt(dl[1]);
					myArena.Drone_array.get(x);
					
				}

				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
   





/* 			System.out.println("Enter a file name to Load");
			String file_name = s.nextLine();
			Scanner myReader = new Scanner(file_name+".txt");
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		  } catch (Exception e) {
			e.printStackTrace();
		  } */
	}
    
	void doDisplay(){
		int x = myArena.get_x();
		int y = myArena.get_y();
		ConsoleCanvas Arena = new ConsoleCanvas(x, y);
		myArena.showDrones(Arena);
		System.out.println(Arena.toString());

	}
	public static void main(String[] args) {
		DroneInterface r = new DroneInterface();	// just call the interface
	}

}