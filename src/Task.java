public class Task {
    private int id;
    private String agence;
    private String guichet;

    public Task(int id, String agence, String guichet) {
        this.id = id;
        this.agence = agence;
        this.guichet = guichet;
    }

    public int getId() {
        return id;
    }

    public String getAgence() {
        return agence;
    }

    public String getGuichet() {
        return guichet;
    }

    @Override
    public String toString() {
        return "Agence: " + agence + "\nTicket Nº: " + id + "\nGuichet Nº: " + guichet;
    }
}
