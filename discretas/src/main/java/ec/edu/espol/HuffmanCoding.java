package ec.edu.espol;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    private Map<Character, String> huffmanCodes = new HashMap<>();
    private HuffmanNode root;

    // Construye el árbol a partir del texto
    public void buildTree(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Crear cola de prioridad ordenada por frecuencia
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Construcción del árbol
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode merged = new HuffmanNode('\0', left.freq + right.freq);
            merged.left = left;
            merged.right = right;

            pq.add(merged);
        }

        root = pq.poll();
        generateCodes(root, "");
    }

    // Genera los códigos binarios de cada carácter
    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.isLeaf()) {
            huffmanCodes.put(node.ch, code);
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    // Codifica el texto
    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            sb.append(huffmanCodes.get(ch));
        }
        return sb.toString();
    }

    // Decodifica el texto binario
    public String decode(String encodedText) {
        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.isLeaf()) {
                result.append(current.ch);
                current = root;
            }
        }
        return result.toString();
    }

    // Muestra los códigos de cada letra
    public void printCodes() {
        System.out.println("Códigos Huffman:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
    }

}
