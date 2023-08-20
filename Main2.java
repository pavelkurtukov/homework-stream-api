import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        // Инициализируем рандомных людей
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // 1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        Stream<Person> stream1 = persons.stream();
        long teenagersCount = stream1.filter(person -> person.getAge() < 18).count();

        // 2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        Stream<Person> stream2 = persons.stream();
        List<String> recruits = stream2.filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.getName() + " " + person.getFamily())
                .toList();

        // 3.Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        Stream<Person> stream3 = persons.stream();
        Comparator<Person> comparatorPersonByName = Comparator.comparing(Person::getFamily).thenComparing(Person::getName);
        List<Person> workables = stream3.filter(person -> person.getAge() >= 18)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> !(person.getSex() == Sex.MAN && person.getAge() > 60))
                .filter(person -> !(person.getSex() == Sex.WOMAN && person.getAge() > 65))
                .sorted(comparatorPersonByName)
                .toList();
    }
}
