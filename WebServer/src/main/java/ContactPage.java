/**
 * Created by 986296 on 5/28/2018.
 */
public class ContactPage {

    private static int noOfTimesPageCalled = 0;

    public String getContent(){
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Welcome Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p></p>\n" +
                "<p>Counter " + (++noOfTimesPageCalled) + "</p>\n" +
                "</body>\n" +
                "</html>";
        return content;
    }
}
