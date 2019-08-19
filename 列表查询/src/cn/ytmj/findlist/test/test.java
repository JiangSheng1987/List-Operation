package cn.ytmj.findlist.test;

/**
 * @author rui
 * @create 2019-08-19 22:48
 */
public class test {
    public static void main(String[] args) {
        String a = "笨蛋";
        String b = "你是大笨蛋";
        if (b.contains(a)) {
            b = b.replaceAll(a, "***");
            System.out.println(b);
        }
    }
}
