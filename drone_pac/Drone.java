package drone_pac;


import drone_pac.DirectionEnum.Direction;


public class Drone {
    private int x_pos;
    private int y_pos;
    private static int count = 0;
    private int Droneid;
    private Direction Dir;


    public Drone(int x, int y, Direction D) {
        x_pos = x;
        y_pos = y;
        Dir = D;
    
        count++;
        Droneid = count;


    }

    public String toString() {
        return "the Drone " + Droneid + " is at x: " + x_pos + " and y: " + y_pos + " in direction " + Dir.name();
    }

    public boolean isHere (int sx, int sy) {
		if ((x_pos == sx) && (y_pos == sy)) {
            return true;
        }		
        else {
            return false;
        }
	}

    public void displayDrone (ConsoleCanvas c){
        c.showIt(x_pos, y_pos, 'D' );

    }


    public void move_drone(DroneArena dArena) {
        int x_direc, y_direc;
        switch (Dir.name()) {
            case "North":
                y_direc = -1;
                if((dArena.canMoveHere((x_pos), (y_pos+y_direc)))){
                    y_pos = y_pos + y_direc; 
                    
                }
                else{
                    Dir = Dir.get_next_pos();
                }
                break;

            case "South":
                y_direc = 1;
                if((dArena.canMoveHere((x_pos), (y_pos+y_direc)))){
                    y_pos = y_pos + y_direc;
                }
                else{
                    Dir = Dir.get_next_pos();
                }            
                break;

            case "West":
                x_direc = -1;
                if((dArena.canMoveHere((x_pos+x_direc), (y_pos)))){
                    x_pos = x_pos + x_direc;
                }
                else{
                    Dir = Dir.get_next_pos();
                }               
                break;

            case "East":
                x_direc = 1;
                if((dArena.canMoveHere((x_pos + x_direc), (y_pos)))){
                    x_pos = x_pos + x_direc;
                }
                else{
                    Dir = Dir.get_next_pos();
                }               
                break;
        }
        
    } 
    

    public static void main(String[] args) {
        Drone d = new Drone(5, 3, Direction.South);		// create drone
        System.out.println(d.toString());	// print where is
    }    
}

