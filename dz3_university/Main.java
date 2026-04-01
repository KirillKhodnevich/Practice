import java.util.List;

public class Main {
    public static void main(String[] args) {
        University university = new University();

        // Создание объектов
        Student student = new Student(1, "Ivan", "10A");
        Subject math = new Subject(1, "Математика");
        Mark mark = new Mark(student, math, (byte) 5, "21.11.2023");

        // Правильные названия методов
        university.addStudent(student);
        university.addSubject(math);
        university.addMark(mark);

        System.out.println("Средний балл: " + university.getAverageMark(student));

        List<Student> excellent = university.getExcellentStudents();
        System.out.println("Отличники: " + excellent.size());

        university.FileCSV();
    }
}