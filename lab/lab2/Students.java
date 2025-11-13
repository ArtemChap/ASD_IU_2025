package lab2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Students {
    private Long id;
    private String name;


    public Students(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        // 1. Создаем коллекции с начальной емкостью для 10 миллионов элементов
        ArrayList<Students> arrayList = new ArrayList<>(10_000_010);
        LinkedList<Students> linkedList = new LinkedList<>();
        HashSet<Students> hashSet = new HashSet<>(10_000_010);
        HashMap<Long, Students> hashMap = new HashMap<>(10_000_010);

        // 2. Заполняем коллекции 10 миллионами объектов Students
        System.out.println("Заполнение коллекций 10 000 000 элементами...");
        for (long i = 1; i <= 10_000_000; i++) {
            arrayList.add(new Students(i, "Aria"));
            linkedList.add(new Students(i, "Layla"));
            hashSet.add(new Students(i, "Stella"));
            hashMap.put(i, new Students(i, "Maya"));
        }

        // 3. Заранее создаем студентов для операций добавления
        //    Чтобы создание объектов не влияло на время операций
        Students firstAria = new Students(0L, "Aria");
        Students lastAria = new Students(10_000_001L, "Aria");

        Students firstLayla = new Students(0L, "Layla");
        Students lastLayla = new Students(10_000_001L, "Layla");

        Students firstStella = new Students(0L, "Stella");
        Students lastStella = new Students(10_000_001L, "Stella");

        Students firstMaya = new Students(0L, "Maya");
        Students lastMaya = new Students(10_000_001L, "Maya");

        // 4. Измеряем время операций для каждой коллекции
        System.out.println("\nДобавление 1 несуществующего элемента в конец (id = 10 000 001):");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.add(lastAria)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.addLast(lastLayla)));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.add(lastStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.put(10_000_001L, lastMaya)));

        System.out.println("\nДобавление 1 несуществующего элемента в начало (id = 0):");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.add(0, firstAria)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.addFirst(firstLayla)));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.add(firstStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.put(0L, firstMaya)));

        System.out.println("\nУдаление последнего элемента:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.remove(arrayList.size() - 1)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.removeLast()));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.remove(lastStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.remove(10_000_001L)));

        System.out.println("\nУдаление первого элемента:");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.remove(0)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.removeFirst()));
        System.out.println("   HashSet: " + runMeasuring(() -> hashSet.remove(firstStella)));
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.remove(0L)));

        System.out.println("\nВзятие центрального элемента (id = 5 000 000):");
        System.out.println(" ArrayList: " + runMeasuring(() -> arrayList.get(5_000_000 - 1)));
        System.out.println("LinkedList: " + runMeasuring(() -> linkedList.get(5_000_000 - 1)));
        System.out.println("   HashSet: " + "<операция не предусмотрена>");
        System.out.println("   HashMap: " + runMeasuring(() -> hashMap.get(5_000_000L)));
    }

    // Метод для измерения времени выполнения операции
    private static long runMeasuring(Runnable function) {
        long startTime = System.nanoTime();  // Засекаем начальное время в наносекундах
        function.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Переопределяем equals и hashCode для корректной работы HashSet и HashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students student = (Students) o;
        return Objects.equals(id, student.id);  // Сравниваем студентов только по id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Хэш-код основан только на id
    }
}