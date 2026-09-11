public class MiListaCircular implements ListInterface {

    private Node head;
    private Node tail;
    private int size;

    public MiListaCircular() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Object getHead() {
        return (head == null) ? null : head.dato;
    }

    @Override
    public Object getTail() {
        return (tail == null) ? null : tail.dato;
    }

    @Override
    public Object get(Node node) {
        return (node == null) ? null : node.dato;
    }

    @Override
    public Node search(Object object) {
        if (isEmpty()) {
            return null;
        }
        Node temp = head;
        do {
            if (temp.dato == null ? object == null : temp.dato.equals(object)) {
                return temp;
            }
            temp = temp.siguiente;
        } while (temp != head);
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insert(Node node, Object object) {
        if (node == null || isEmpty()) {
            return false;
        }

        if (node == head) {
            return insertHead(object);
        }

        Node pred = head;
        for (int i = 0; i < size; i++) {
            if (pred.siguiente == node) {
                break;
            }
            pred = pred.siguiente;
        }
        if (pred.siguiente != node) {
            return false;
        }

        Node nuevo = new Node(object);
        nuevo.siguiente = node;
        pred.siguiente = nuevo;
        size++;
        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        Node nodeRef = search(objectRef);
        if (nodeRef == null) {
            return false;
        }
        return insert(nodeRef, object);
    }

    @Override
    public boolean insertHead(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            head = nuevo;
            tail = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            nuevo.siguiente = head;
            head = nuevo;
            tail.siguiente = head;
        }
        size++;
        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            head = nuevo;
            tail = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            tail.siguiente = nuevo;
            nuevo.siguiente = head;
            tail = nuevo;
        }
        size++;
        return true;
    }

    @Override
    public boolean set(Node node, Object object) {
        if (node == null) {
            return false;
        }
        node.dato = object;
        return true;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null || isEmpty()) {
            return false;
        }

        if (size == 1) {
            if (node != head) {
                return false;
            }
            clear();
            return true;
        }

        if (node == head) {
            head = head.siguiente;
            tail.siguiente = head;
            size--;
            return true;
        }

        Node pred = head;
        for (int i = 0; i < size; i++) {
            if (pred.siguiente == node) {
                break;
            }
            pred = pred.siguiente;
        }
        if (pred.siguiente != node) {
            return false;
        }

        pred.siguiente = node.siguiente;
        if (node == tail) {
            tail = pred;
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node temp = head;
        for (int i = 0; i < size; i++) {
            array[i] = temp.dato;
            temp = temp.siguiente;
        }
        return array;
    }

    @Override
    public Object[] toArray(Object[] object) {
        if (object.length < size) {
            object = new Object[size];
        }
        Node temp = head;
        for (int i = 0; i < size; i++) {
            object[i] = temp.dato;
            temp = temp.siguiente;
        }
        if (object.length > size) {
            object[size] = null;
        }
        return object;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        MiListaCircular sub = new MiListaCircular();
        if (from == null || to == null || isEmpty()) {
            return sub;
        }

        Node temp = from;
        for (int i = 0; i < size; i++) {
            sub.insertTail(temp.dato);
            if (temp == to) {
                break;
            }
            temp = temp.siguiente;
        }
        return sub;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MiListaCircular sortList() {
        MiListaCircular sorted = new MiListaCircular();
        if (isEmpty()) {
            return sorted;
        }

        Node temp = head;
        for (int i = 0; i < size; i++) {
            Comparable<Object> actual = (Comparable<Object>) temp.dato;

            if (sorted.isEmpty() || actual.compareTo(sorted.head.dato) <= 0) {
                sorted.insertHead(temp.dato);
            } else {
                Node nodoSorted = sorted.head;
                int j = 0;
                while (j < sorted.size - 1
                        && actual.compareTo(nodoSorted.siguiente.dato) > 0) {
                    nodoSorted = nodoSorted.siguiente;
                    j++;
                }
                if (nodoSorted == sorted.tail) {
                    sorted.insertTail(temp.dato);
                } else {
                    sorted.insert(nodoSorted.siguiente, temp.dato);
                }
            }
            temp = temp.siguiente;
        }
        return sorted;
    }

    public boolean update(Object valorViejo, Object valorNuevo) {
        if (isEmpty()) {
            return false;
        }
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.dato == null ? valorViejo == null : temp.dato.equals(valorViejo)) {
                temp.dato = valorNuevo;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    public boolean delete(Object elementoABorrar) {
        Node node = search(elementoABorrar);
        return remove(node);
    }

    public void recorrerCabezaACola() {
        if (isEmpty()) {
            System.out.println("(lista vacia)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        do {
            sb.append(temp.dato);
            if (temp != tail) {
                sb.append(" -> ");
            }
            temp = temp.siguiente;
        } while (temp != head);
        sb.append(" -> (vuelve a la cabeza)");
        System.out.println(sb);
    }

    public void recorrerConSize() {
        if (isEmpty()) {
            System.out.println("(lista vacia)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        for (int i = 0; i < size; i++) {
            sb.append(temp.dato);
            if (i < size - 1) {
                sb.append(" -> ");
            }
            temp = temp.siguiente;
        }
        sb.append(" -> (vuelve a la cabeza)");
        System.out.println(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node temp = head;
        for (int i = 0; i < size; i++) {
            sb.append(temp.dato);
            if (i < size - 1) {
                sb.append(", ");
            }
            temp = temp.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}