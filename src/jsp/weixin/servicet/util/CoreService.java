package jsp.weixin.servicet.util;
/**
 * @author Engineer-Jsp
 * @date 2014.09.23
 * ����Service��*/
import java.util.Date;
import java.util.Map;
import jsp.weixin.msg.Resp.TextMessage;
import jsp.weixin.msg.Util.MessageUtil;
public class CoreService {

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(String request) {
		// xml��ʽ����Ϣ���
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ���ͣ�";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "���͵����ı���Ϣ��";
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "���͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "���͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "���͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "���͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "���͵���������Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл��Ĺ�ע��";
				}
				// ȡ���ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ���ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ�������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ�������ά���¼�
				}
				// �ϱ�����λ��
				else if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
					// TODO �����ϱ�����λ���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO ����˵�����¼�
//					 �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ  
                    String eventKey = requestMap.get("EventKey");	
                    System.out.println("EventKey="+eventKey);
                    respContent = "����Ĳ˵�KEY:"+eventKey;
				}
			}
			// �����ı���Ϣ������
			textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			respXml = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

}
