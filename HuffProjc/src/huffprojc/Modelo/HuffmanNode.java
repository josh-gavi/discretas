/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffprojc.Modelo;

/**
 *
 * @author Kailo
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    public char ch;
    public int freq;
    public HuffmanNode left, right;

    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.freq - o.freq;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
