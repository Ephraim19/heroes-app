import java.util.ArrayList;

public class Squad {
    private Integer maxSize = 0;
    private String name;
    private  String dedicatedCause;
    private Hero heroSquad;
    private static ArrayList<Squad> instance = new ArrayList<>();

    public Squad(Integer maxSize,String name, String dedicatedCause,Hero hero) {
        this.maxSize = maxSize;
        this.name = name;
        this.dedicatedCause = dedicatedCause;
        heroSquad = hero;
        instance.add(this);
    }


    public Integer getMaxSize() {
        return maxSize;
    }

    public String getName() {
        return name;
    }

    public String getDedicatedCause() {
        return dedicatedCause;
    }

    public Hero getData(){
        return heroSquad;
    }
    public static ArrayList<Squad> getAllData(){
        return instance;
    }
    public static boolean getSize() {
        return true;
    }
    public static void clearAllSquads(){
        instance.clear();
    }
}

