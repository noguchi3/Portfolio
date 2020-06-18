package todo.dto;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
 
/**
 * TODO�������ʂP�s�P�ʂ�ValueObject
 *
 */
public class Todo {
	/** TODO��ID */
	private int id;
 
	/** TODO�̃^�C�g�� */
	private String title;
 
	/** �ڍׂȃ^�X�N�̖��O */
	private String task;
 
	/** �^�X�N�̊��� */
	private Timestamp limitdate;
 
	/** �^�X�N�̍ŏI�X�V���� */
	private Timestamp lastupdate;
 
	/** �^�X�N�̃��[�UID */
	private String userid;
 
	/** �^�X�N�̃X�e�[�^�X�R�[�h */
	private int status;
 
	/** �^�X�N�̃X�e�[�^�X�\�� */
	private String label;
 
	/** �o�^��ʂœ��͂��ꂽ���� */
	private String inputLimitdate;
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getTask() {
		return task;
	}
 
	public void setTask(String task) {
		this.task = task;
	}
 
	public Timestamp getLimitdate() {
		return limitdate;
	}
 
	public void setLimitdate(Timestamp limitdate) {
		this.limitdate = limitdate;
	}
 
	public Timestamp getLastupdate() {
		return lastupdate;
	}
 
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}
 
	public String getUserid() {
		return userid;
	}
 
	public void setUserid(String userid) {
		this.userid = userid;
	}
 
	public int getStatus() {
		return status;
	}
 
	public void setStatus(int status) {
		this.status = status;
	}
 
	public String getLabel() {
		return label;
	}
 
	public void setLabel(String label) {
		this.label = label;
	}
 
	public String getInputLimitdate() {
		return inputLimitdate;
	}
 
	public void setInputLimitdate(String inputLimitdate) {
		this.inputLimitdate = inputLimitdate;
	}
 
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TodoValueObject [id=");
		builder.append(id);
		builder.append(", ");
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (task != null) {
			builder.append("task=");
			builder.append(task);
			builder.append(", ");
		}
		if (limitdate != null) {
			builder.append("limit=");
			builder.append(limitdate);
			builder.append(", ");
		}
		if (lastupdate != null) {
			builder.append("lastupdate=");
			builder.append(lastupdate);
			builder.append(", ");
		}
		if (userid != null) {
			builder.append("userid=");
			builder.append(userid);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (label != null) {
			builder.append("label=");
			builder.append(label);
			builder.append(", ");
		}
		if (inputLimitdate != null) {
			builder.append("inputLimitdate=");
			builder.append(inputLimitdate);
		}
		builder.append("]");
		return builder.toString();
	}
 
	/**
	 * ���̓`�F�b�N���s���B �������̓`�F�b�N�G���[���������ꍇ�ɂ͎����I�ɃG���[���b�Z�[�W���ǉ������B
	 */
	public boolean valueCheck() {
		// �G���[���b�Z�[�W�̏�����
		errorMessages = new ArrayList<String>();
 
		// id
		if (id < 0) {
			errorMessages.add("�s���ȓ��͂����o���܂���");
		}
 
		// title
		if (title == null || title.isEmpty()) {
			errorMessages.add("���͂����^�C�g������ł�");
		} else if (title.length() > 256) {
			errorMessages.add("���͂����^�C�g�����������܂�");
		}
 
		// task
		if (task == null || task.isEmpty()) {
			errorMessages.add("���͂����^�X�N����ł�");
		} else if (title.length() > 512) {
			errorMessages.add("���͂����^�X�N���������܂�");
		}
 
		// limitdate
		if (inputLimitdate == null || inputLimitdate.isEmpty()) {
			errorMessages.add("���͂����^�X�N��������ł�");
		} else if (!inputLimitdate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			errorMessages.add("���͂����^�X�N�����̃t�H�[�}�b�g���Ⴂ�܂�");
		}
 
		// userid
		if (userid == null || userid.isEmpty()) {
			errorMessages.add("���͂������[�U�[ID����ł�");
		} else if (userid.length() > 64) {
			errorMessages.add("���͂������[�U�[ID���������܂�");
		}
 
		// status
		if (status < 0 || status > 3) {
			errorMessages.add("���͂����X�e�[�^�X�̒l���s���ł�");
		}
 
		return (errorMessages.size() == 0);
	}
 
	private List<String> errorMessages;
 
	public List<String> getErrorMessages() {
		return errorMessages;
	}
 
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}