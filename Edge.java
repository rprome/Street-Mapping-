public class Edge {
    private String id;
    private String from;
    private String to;
    private double length;

    public Edge(String id, String from, String to, double length){
        this.id = id;
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public String getNeighbor (String nodeName){
        if (from.equals(nodeName)){
            return to;
        }else{
            return from;
        }
    }
    public String getFrom(){
        return from;
    }
    public void setFrom(String from){
        this.from = from;
    }
    public String getTo(){
        return to;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double length){
        this.length= length;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }


}
