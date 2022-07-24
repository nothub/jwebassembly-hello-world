package lol.hub;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.web.dom.Document;
import de.inetsoftware.jwebassembly.web.dom.HTMLElement;
import de.inetsoftware.jwebassembly.web.dom.Window;

public class Main {
    @Export
    public static void main() {
        Document document = Window.document();

        // de.inetsoftware.jwebassembly.web.dom.Document
        // does not allow access to the head element,
        // means this is not possible without workarounds?
        /* HTMLElement title = document.createElement("title");
        title.appendChild(document.createTextNode("JWebAssembly Example"));
        document.head().appendChild(title); */

        HTMLElement div = document.createElement("div");
        div.appendChild(document.createTextNode("Hello World, this text come from WebAssembly."));
        document.body().appendChild(div);

        HTMLElement canvas = document.createElement("canvas");
        document.body().appendChild(canvas);
    }
}
