package pro.chenggang.project.greenwich.provider.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Evans on 2017/7/30.
 */
@Getter
@Setter
@ToString
public class EmailParam {

	@NotNull
	private List<String> receiver;
	private List<String> ccReceiver;
	private List<String> bccReceiver;
	@NotNull
	private String subject;
	private String mailContent;

	private MultipartFile[] attachmentFiles;


}
