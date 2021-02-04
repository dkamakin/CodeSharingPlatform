package platform;

public class CodeModel {

    private String code;
    private Time time;

    public CodeModel() {
        time = new Time();
        code = "public static..";
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Time getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }

    public Time updateTime() {
        return time.update();
    }

    public String getCodeHTMLNew() {
        return "<html>\n" +
                "<head>\n" +
                "   <title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "   <textarea id=\"code_snippet\">\n" +
                "// write your code here" +
                "</textarea>\n" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">\n" +
                "Submit" +
                "</button>\n" +
                "</body>\n" +
                "</html>\n";
    }

    public String getCodeJSON() {
        return "{\n" +
                "\"code\": \"" + code + "\",\n" +
                "\"date\": \"" + time.getTime() + "\"" +
                "\n}";
    }

    public String getCodeHTML() {
        return "<html>\n" +
                "<head>\n" +
                "   <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "   <pre id=\"code_snippet\">\n" +
                code +
                "</pre>\n" +
                "   <span id=\"load_date\">\n\t" +
                time.getTime() +
                "</span>\n" +
                "</body>\n" +
                "</html>\n";
    }
}