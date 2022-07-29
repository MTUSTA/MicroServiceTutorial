package messaging;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TalimatBildirim {
    private String hesapId;
    private String talimatId;
    private String talimatTanimi;
}
