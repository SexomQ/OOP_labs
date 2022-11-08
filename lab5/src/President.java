public class President implements Test{
    private String country;
    private String name;
    private int fee;
    public String status = "test";

    public String getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
}
