package drone_pac;



import java.util.ArrayList;
import java.util.Random;

import drone_pac.DirectionEnum.Direction;

public class DroneArena {
    private int x,y;  // sets the hieght and width of the arena
    private Drone drone;
    ArrayList <Drone> Drone_array;


    Random randnum;

    public DroneArena (int height, int width){
        x = width;
        randnum = new Random();
        y = height;
        Drone_array = new ArrayList<Drone>();
        
    }

    
    public void addDrone (){
        int x_rand,y_rand;
        do {
            x_rand = randnum.nextInt(1,x-1);
            y_rand = randnum.nextInt(1,y-1);
            
        } while (getDrone(x_rand, y_rand)!= null);
        drone = new Drone(x_rand, y_rand, Direction.get_random_Direction());
        Drone_array.add(drone);
        

    }

    public String toString(){
        String textString;
        textString = "size : "+x+" wide and "+y+" high ,Drone: " + "\n";
        for (int i = 0; i < Drone_array.size(); i++) {
            textString = textString + Drone_array.get(i).toString() + "\n";

        }
        
        return textString;


    }

    public Drone getDrone( int x, int y){
        for (int i = 0; i < Drone_array.size(); i++) {
            if (Drone_array.get(i).isHere(x, y) == true) {
                return Drone_array.get(i);
                
            }
            
            
            
        }
        return null;
    }

    public void showDrones(ConsoleCanvas c){
        for(Drone d: Drone_array){
            d.displayDrone(c);
        }
        
    }

    public static int get_x(DroneArena a){
        return a.x; //gets the x values from drone object a 
    }

    public static int get_y(DroneArena a){
        return a.y; //gets the y values from drone object a 
    }

    public void moveAllDrones(DroneArena da){
        for (Drone d : Drone_array){
            d.move_drone(da);
        }
    }

    public boolean canMoveHere(int x_pos, int y_pos) {
        for (Drone d : Drone_array){
            if ((d.isHere(x_pos, y_pos)) || ((x_pos == 0) || (x_pos == (x - 1)) || (y_pos == 0) || (y_pos == (y - 1)))){     //check for any obsticles such as arena borders and other drones and if it is in the arena
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
		DroneArena a = new DroneArena(20, 10);	// create drone arena
		a.addDrone();
        a.addDrone();
		System.out.println(a.toString());	// print where is
	}

}