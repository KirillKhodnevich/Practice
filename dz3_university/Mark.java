public class Mark {
    private Student student;
    private Subject subject;
    private byte value;
    private String date;

    public Mark (Student student, Subject subject, byte value, String date) {
        this.student = student;
        this.subject = subject;
        this.value = value;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public byte getValue() {
        return value;
    }
}