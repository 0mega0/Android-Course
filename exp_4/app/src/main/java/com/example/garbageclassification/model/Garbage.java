package com.example.garbageclassification.model;

public class Garbage implements Comparable<Garbage>{
    public enum GarbageType{
        kitchen(1),recycle(2),harmful(3),others(4);
        public final int typeValue;

        GarbageType(int typeValue) {
            this.typeValue = typeValue;
        }

        public String toString() {
            String str = "";
            switch (typeValue) {
                case 1:
                    str = "厨余垃圾";
                    break;
                case 2:
                    str = "可回收垃圾";
                    break;
                case 3:
                    str = "有害垃圾";
                    break;
                case 4:
                    str = "其他垃圾";
                    break;

            }
            return str;
        }

    }
    private String name;
    private GarbageType type;

    public Garbage() {}
    public Garbage(String name, GarbageType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GarbageType getType() {
        return type;
    }

    public void setType(GarbageType type) {
        this.type = type;
    }

    public String toString(){
        return name + "属于" + type.toString() + "\n应投入“"+ type.toString() +"”垃圾桶中";
    }
    @Override
    public int compareTo(Garbage garbage) {
        return type.typeValue - garbage.getType().typeValue;
    }
}
