package population;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
	// write your code here
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
       long count = persons.stream()
                .filter((a) -> a.getAge() < 18)
               .count();
        System.out.print("Колличество несовершеннрлетних  - ");
        System.out.println(count);
        List<String> conscript =persons.stream()
                .filter((p)-> p.getAge() >= 18 &&
                        p.getAge() < 27 && p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список призывников");
        System.out.println(conscript);
        List<String> education =persons.stream()
                .filter((p) -> p.getEducation()==Education.HIGHER)
                .filter((p) -> p.getAge()>=18 && p.getAge() < 65 &&
                        p.getSex() == Sex.MAN || p.getAge()>=18 &&
                        p.getAge() < 60 && p.getSex() == Sex.WOMAN)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Потенциальные работники:");
        System.out.println(conscript);
    }
}
