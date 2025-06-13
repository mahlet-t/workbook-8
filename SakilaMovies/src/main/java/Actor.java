public class Actor {
    int actorId;
    String firstName;
    String lastname;

    public Actor(int actorId, String firstName, String lastname) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-10s %-10s", actorId, firstName, lastname+
                "\n............................................................");
    }
}
