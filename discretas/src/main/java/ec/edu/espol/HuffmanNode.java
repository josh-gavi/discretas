package ec.edu.espol;

public class HuffmanNode implements Comparable<HuffmanNode> {
    char ch;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq; // menor frecuencia primero
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

}
