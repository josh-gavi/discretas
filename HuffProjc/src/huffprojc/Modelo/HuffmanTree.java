/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffprojc.Modelo;

/**
 *
 * @author Kailo
 */
import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> huffmanCodes;

    public HuffmanTree(String text) {
        build(text);
    }

    private void build(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (var e : freqMap.entrySet()) {
            pq.add(new HuffmanNode(e.getKey(), e.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode n1 = pq.poll();
            HuffmanNode n2 = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', n1.freq + n2.freq);
            parent.left = n1;
            parent.right = n2;
            pq.add(parent);
        }

        root = pq.poll();
        huffmanCodes = new HashMap<>();
        buildCodes(root, "");
    }

    private void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            huffmanCodes.put(node.ch, code);
        }
        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(huffmanCodes.get(c));
        }
        return sb.toString();
    }

    public String decode(String encoded) {
        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.isLeaf()) {
                result.append(current.ch);
                current = root;
            }
        }
        return result.toString();
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public Map<Character, String> getCodes() {
        return huffmanCodes;
    }
}
