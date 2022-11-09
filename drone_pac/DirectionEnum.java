package drone_pac;

import java.util.*;


public class DirectionEnum {

    static Random rand_direction = new Random();
    public enum Direction {
        South,
        North,
        East,
        West;

        public static final Direction[] value = values();
        
        public static Direction get_random_Direction(){
            return values()[rand_direction.nextInt(values().length)];
        }

        public Direction get_next_pos(){
            return value[(this.ordinal()+1) % value.length];
        }
    }
    
}

