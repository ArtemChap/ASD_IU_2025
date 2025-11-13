package lab2;

import java.util.HashMap;
import java.util.Map;


// обьялвяем класс с параметрами ключа и значения элементов
class Node<K,V>{
    K key;
    V value;
    Node<K,V> prev;
    Node<K,V> next;
    public Node(){}
    public Node(K key, V value){ //конструткор для создания узлов
        this.key = key;
        this.value = value;
    }
}


public class LRUCache<K, V> { // основной класс с типом K и V
    private final int capacity;
    private final Map<K, Node<K,V>> cache;
    private final Node<K,V> head;
    private final Node<K,V> tail;

    public LRUCache(int capacity) { //максимальная ёмкость кэша
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key){ // метод получения ключа
        Node<K,V> node = cache.get(key);
        if(node == null){
            return null;
        }
        moveToHead(node);

        return node.value;
    }


    public void put(K key, V value){ // метод добавления или обновления элемента
        Node<K,V> node = cache.get(key);
        if(node == null){
            Node<K,V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            if (cache.size() > capacity){
                Node<K,V> tailNode = removeTail();
                cache.remove(tailNode.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public boolean remove(K key){ // метод для удаления  элемента
        Node<K,V> node = cache.get(key);
        if(node == null){
            return false;
        }
        removeNode(node);
        cache.remove(key);
        return true;
    }

    public int size(){
        return cache.size();
    }

    public boolean isEmpty(){
        return cache.isEmpty();
    }

    public void clear(){
        cache.clear();
        head.next = tail;
        tail.prev = head;
    }


    private void addToHead(Node<K,V> node){ // метод добавления узла в начало списка
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K,V> node){ // метод удаления узла из списка
        Node<K,V> prev = node.prev;
        Node<K,V> next = node.next;
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
    }


    private void moveToHead(Node<K,V> node){ // метод перемещения узла в начало списка
        removeNode(node);
        addToHead(node);
    }


    private Node<K,V> removeTail(){ // метод перемещения узла в конец
        Node<K,V> res = tail.prev;
        removeNode(res);
        return res;
    }

    public void printCache(){ // метод вывода кэша
        Node<K,V> current = head.next;

        while(current != tail) {
            System.out.println("[" + current.key + "=" + current.value + "]");
            current = current.next;
        }
        System.out.println();
    }

    // тестирование на работоспособность


    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        System.out.println(" Тестирование LRU Cache) ");
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.put(4, "Four");
        cache.printCache();


        System.out.println("Получение ключа 2: " + cache.get(2));
        cache.printCache();

        cache.put(5, "Five");
        cache.printCache();
        System.out.println(" Получение ключа 1: " + cache.get(1));


        cache.put(3, "обновленный");
        cache.printCache();

        cache.remove(4);
        cache.printCache();

        System.out.println("Длина перед очисткой: " + cache.size());

        System.out.println("Проверка пустоты: " + cache.isEmpty());

    }
}