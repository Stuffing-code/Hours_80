public class Main {
    public static void main(String[] args) {
        ChromeTest chr = new ChromeTest();
        chr.beforeTest("http://www.google.com");
        chr.sendKeysTest();
        chr.singInTest();
//        chr.clearTest();
//        chr.beforeTest("http://www.vk.com");
//        chr.afterTest();
    }
}
