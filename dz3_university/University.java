import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;
import java.nio.file.Files;
import java.nio.file.Paths;

public class University {
    private List<Student> students = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();

    //добавить ученика
    public void addStudent(Student student) {
        this.students.add(student);
    }

    //добавить предмет
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    //добавить оценку
    public void addMark(Mark mark) {
        this.marks.add(mark);
    }

    //вывести средний балл ученика
    public double getAverageMark(Student student) {
        return marks.stream()
                .filter(mark -> mark.getStudent().equals(student))
                .mapToDouble(mark -> mark.getValue())
                .average()
                .orElse(0.0);
    }

    //найти предмет с наивысшим средним баллом
    public Subject MaxAverageMark() {
        return subjects.stream()
                .map(subject -> {
                    double averageMark = marks.stream()
                            .filter(mark -> mark.getSubject().equals(subject))
                            .mapToDouble(mark -> mark.getValue())
                            .average()
                            .orElse(0.0);
                    return new Object[] {subject, averageMark};
                })
                .max(Comparator.comparingDouble(arr -> (Double) arr[1]))
                .map(arr -> (Subject) arr[0])
                .orElse(null);
    }

    //получить список отличников (средний балл > 4.5)
    public List<Student> getExcellentStudents() {
        return students.stream()
                .filter(student -> getAverageMark(student) > 4.5)
                .collect(Collectors.toList());
    }

    //вывести статистику по предмету (средний, минимальный и максимальный балл)
    public SubjectStats getStatistics(Subject subject) {
        DoubleSummaryStatistics stats = marks.stream()
                .filter(mark -> mark.getSubject().equals(subject))
                .mapToDouble(Mark::getValue)
                .summaryStatistics();

        return new SubjectStats(
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }

    //отсортировать учеников по средней оценке
    public List<Student> getSortedStudents() {
        return students.stream()
                .map(student -> {
                    double averageMark = getAverageMark(student);
                    return new Object[] {student, averageMark};
                })
                .sorted(Comparator.comparingDouble(arr -> (Double) arr[1]))
                .map(arr -> (Student) arr[0])
                .collect(Collectors.toList());
    }

    //*сохранить список лучших учеников в CSV-файл
    public void FileCSV() {
        List<Student> bestStudents = getExcellentStudents();

        List<String> lines = bestStudents.stream()
                .map(student -> student.getId() + "," +
                        student.getName() + "," +
                        student.getClassName() + "," +
                        String.format("%.2f", getAverageMark(student)))
                .collect(Collectors.toList());

        // Добавляем заголовок
        lines.add(0, "ID,Name,Class,Average");

        try {
            Files.write(Paths.get("best_students.csv"), lines);
            System.out.println("Файл создан!");
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }
}