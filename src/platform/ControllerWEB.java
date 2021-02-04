package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerWEB {
    private final ControllerSnippets snippets;

    @Autowired
    public ControllerWEB(ControllerSnippets snippets) {
        this.snippets = snippets;
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> getCodeNew() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        CodeModel code = new CodeModel();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(code.getCodeHTMLNew());
    }

    @GetMapping("/code/latest")
    public ResponseEntity<String> HTMLLatest() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        StringBuilder builder = new StringBuilder();
        List<CodeModel> codes = snippets.getLatestSnippets();

        builder.append("<html>\n" +
                "<head>\n" +
                "   <title>Latest</title>\n" +
                "</head>\n" +
                "<body>\n");

        for (CodeModel code : codes)
            builder.append("   <pre id=\"code_snippet\">\n").append(code.getCode()).append("</pre>\n").append("   <span id=\"load_date\">\n\t").append(code.getTime().getTime()).append("</span>\n");

        builder.append("</body>\n" +
                "</html>\n");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(builder.toString());
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<String> HeadersHTML(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        int number = Integer.parseInt(id);
        CodeModel code;

        try {
            code = snippets.getSnippetAt(number - 1);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.badRequest()
                    .headers(responseHeaders)
                    .body("{}");
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(code.getCodeHTML());
    }
}
