package com.mesilat;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.storage.macro.MacroId;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.template.TemplateRenderer;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

@Scanned
public class SwitchMacro implements Macro {
    private final TemplateRenderer renderer;

    @Override
    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }
    @Override
    public OutputType getOutputType() {
        return OutputType.INLINE;
    }
    @Override
    public String execute(Map params, String body, ConversionContext conversionContext) throws MacroExecutionException {
        MacroDefinition macroDefinition = (MacroDefinition)conversionContext.getProperty("macroDefinition");
        MacroId macroId = macroDefinition.getMacroId().getOrNull();

        Map<String,Object> map = new HashMap<>();
        map.put("label", params.get("label"));
        map.put("macroId", macroId == null? "": macroId.getId());
        map.put("body",  body == null? "": body);
        return renderFromSoy("Mesilat.Switch.Templates.control.soy", map);
    }

    @Inject
    public SwitchMacro(
        final @ComponentImport TemplateRenderer renderer
    ){
        this.renderer = renderer;
    }

    public String renderFromSoy(String soyTemplate, Map soyContext) {
        StringBuilder output = new StringBuilder();
        renderer.renderTo(output, "com.mesilat.switch-control:resources", soyTemplate, soyContext);
        return output.toString();
    }
}