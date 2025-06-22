package ec.edu.espol;

public class Main {
    public static void main(String[] args) {
        String text = "ejemplo de codificación de huffman";
        HuffmanCoding huffman = new HuffmanCoding();
        huffman.buildTree(text);
        huffman.printCodes();
        String encoded = huffman.encode(text);
        System.out.println("\nTexto codificado:\n" + encoded);
        String decoded = huffman.decode(encoded);
        System.out.println("\nTexto decodificado:\n" + decoded);
    }
}