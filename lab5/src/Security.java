public class Security implements Test{
    private String name;
    private String rank;
    private int table;
    public String status = "in development";

    public String getStatus() {
        return status;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public int getTable() {
        return table;
    }
}
