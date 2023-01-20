import java.sql.*;

public class Main {
    public static void main(String[] args) {

        final String URL = "jdbc:postgresql://localhost:5432/it.academy";
        final String USER_NAME = "postgres";
        final String PASSWORD = "null";

        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement statement = connection.createStatement();
            String SQL = "select count(*) as amount_students from students where lower(name) like ('%a%')";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println("Получить количество студентов в имени которых содержится буква «а»");
                System.out.println(resultSet.getInt(1));
            }

            SQL = "select max(age) as max_age from students";
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println("Получить максимальный возраст среди студентов");
                System.out.println(resultSet.getInt(1));
            }

            SQL = "select student_id, students.name, cast(avg(grade) as decimal(7, 2)) from grades\n" +
                    "join students on(grades.student_id = students.id)\n" +
                    "group by student_id, students.name order by student_id";
            resultSet = statement.executeQuery(SQL);
            System.out.println("Получить среднюю оценку среди студентов");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("student_id") +
                        " - " + resultSet.getString("name") +
                        " - " + resultSet.getDouble("avg")
                );
            }

            SQL = "select count(*) as amount_students from students";
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println("Получить кол-во всех студентов");
                System.out.println(resultSet.getInt(1));
            }

            SQL = "select min(age) as min_age from students";
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println("Получить возраст самого младшего студента");
                System.out.println(resultSet.getInt(1));
            }

            SQL = "select students.name, grades.student_id, sum(grade) as sum_grade from grades\n" +
                    "join students on (students.id = grades.student_id)\n" +
                    "group by student_id, students.name order by students.name";
            resultSet = statement.executeQuery(SQL);
            System.out.println("Получить сумму оценок всех студентов");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("student_id") +
                        " - " + resultSet.getString("name") +
                        " - " + resultSet.getDouble("sum_grade"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
