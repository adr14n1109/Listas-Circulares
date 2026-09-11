public class Main {
    public static void main(String[] args) {
        MiListaCircular lista = new MiListaCircular();
        
        lista.insertTail(6);
        lista.insertTail(4);
        lista.insertTail(1);
        lista.insertTail(3);

        System.out.println("Lista inicial: " + lista);
        System.out.print("Recorrido Opcion 1 (cabeza a cola) -> ");
        lista.recorrerCabezaACola();
        System.out.print("Recorrido Opcion 2 (con size)      -> ");
        lista.recorrerConSize();

        System.out.println("\nTamano: " + lista.getSize());
        System.out.println("Cabeza: " + lista.getHead());
        System.out.println("Cola:   " + lista.getTail());

        Node cola = lista.search(3);
        System.out.println("La cola apunta de vuelta a la cabeza? "
                + (lista.get(cola.siguiente).equals(lista.getHead())));

        Node nodo1 = lista.search(1);
        System.out.println("\ncontains(1): " + lista.contains(1));
        System.out.println("search(1) encontrado con dato: " + lista.get(nodo1));

        lista.insertHead(9);
        System.out.println("\nDespues de insertHead(9): " + lista);

        Node nodoUno = lista.search(1);
        lista.insert(nodoUno, 99);
        System.out.println("Despues de insertar 99 antes de 1: " + lista);

        lista.update(1, 0);
        System.out.println("\nDespues de update(1, 0): " + lista);


        lista.delete(0);
        System.out.println("Despues de delete(0): " + lista);

        Node nodoCola = lista.search(3);
        lista.set(nodoCola, 30);
        System.out.println("\nDespues de set(nodo(3), 30): " + lista);

        Object[] arreglo = lista.toArray();
        System.out.print("\ntoArray(): ");
        for (Object o : arreglo) {
            System.out.print(o + " ");
        }
        System.out.println();

        Node desde = lista.search(4);
        Node hasta = lista.search(30);
        MiListaCircular sub = lista.subList(desde, hasta);
        System.out.println("subList(4, 30): " + sub);

        MiListaCircular ordenada = lista.sortList();
        System.out.println("\nsortList(): " + ordenada);

        Node nodoAEliminar = lista.search(99);
        lista.remove(nodoAEliminar);
        System.out.println("\nDespues de remove(nodo(99)): " + lista);

        lista.clear();
        System.out.println("\nDespues de clear(): " + lista + " | isEmpty(): " + lista.isEmpty());

        System.out.println("\n--- Ejemplo del trencito (A..H) ---");
        MiListaCircular tren = new MiListaCircular();
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (String letra : letras) {
            tren.insertTail(letra);
        }
        System.out.println("Tren inicial: " + tren);
        tren.update("C", "Z");
        System.out.println("Despues de update('C','Z'): " + tren);
    }
}