import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Person.Sex.values()[new Random().nextInt(Person.Sex.values().length)],
                    Person.Education.values()[new Random().nextInt(Person.Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();

        System.out.println("старше 18");
        System.out.println(count + "\n");


        List<String> collect = persons.stream()
                .filter(sex -> sex.getSex() == Person.Sex.MAN)
                .filter(age -> age.getAge() >= 18 && age.getAge() <= 27)
                .map(e -> e.getFamily())
                .collect(Collectors.toList());
        System.out.println("Призыв:");
        System.out.println(collect + "\n");


        List<Person> personList = persons.stream()
                .filter(x -> {
                    if (x.getSex() == Person.Sex.MAN) {
                        if (x.getAge() >= 18 && x.getAge() <= 65) {
                            return true;
                        }
                    } else if (x.getSex() == Person.Sex.WOMAN) {
                        if (x.getAge() >= 18 && x.getAge() <= 60) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(" работоспособных людей с высшим образованием :");
        personList.forEach(System.out::println);

    }

}

