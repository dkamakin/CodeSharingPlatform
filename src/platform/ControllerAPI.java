package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerAPI {
    private final ControllerSnippets snippets;

    @Autowired
    public ControllerAPI(ControllerSnippets snippets) {
        this.snippets = snippets;
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> postCode(@RequestBody CodeModel codeModel) {
        codeModel.updateTime();
        snippets.addSnippet(codeModel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("{ \"id\": \"" + snippets.getSize() + "\"}");
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<String> HeadersJSON(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        int number = Integer.parseInt(id);
        CodeModel snippet;

        try {
            snippet = snippets.getSnippetAt(number - 1);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.badRequest()
                    .headers(responseHeaders)
                    .body("{}");
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(snippet.getCodeJSON());
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<String> JSONLatest() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        StringBuilder builder = new StringBuilder();
        builder.append("[\n");

        List<CodeModel> codes = snippets.getLatestSnippets();

        for (int i = 0; i < codes.size(); i++)
            builder.append(codes.get(i)
                    .getCodeJSON())
                    .append(i + 1 == codes.size() || i == 9 ? "\n" : ",\n");

        builder.append("]\n");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(builder.toString());
    }

}
