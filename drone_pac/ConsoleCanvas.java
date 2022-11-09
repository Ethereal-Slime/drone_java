package drone_pac;


public class ConsoleCanvas {
/*     private int lenght;
    private int widthx; */
    private String grid[][];

    public ConsoleCanvas(int x, int y){

        grid = new String[x][y];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 || i == grid.length - 1) {
                    grid[i][j] = "#";
                } else if (j == 0 || j == grid[i].length - 1) {
                    grid[i][j] = "#";
                } else {
                    grid[i][j] = " ";
                }
            }
        }

       
    }

    public void showIt(int x, int y, char d){
        grid[x][y] = "D";
        
    }


    public String toString(){
        String arena = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                arena = arena + grid[i][j];
                
            }
            arena = arena + "\n";
        }
        return arena;
    }

    public static void main(String[] args) {
		ConsoleCanvas c = new ConsoleCanvas (10, 20);	// create a canvas
		c.showIt(4,3,'D');								// add a Drone at 4,3
		System.out.println(c.toString());				// display result
	}

}
