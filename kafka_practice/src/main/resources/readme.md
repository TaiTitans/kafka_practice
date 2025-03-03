Giải thích một số config quan trọng:


## Producer
acks=all: Đảm bảo tất cả replicas đã nhận message trước khi xác nhận thành công.

retries=3: Tự động thử lại nếu gửi message thất bại.

batch-size=16384: Kích thước batch để gửi message hiệu quả hơn.

linger-ms=5: Delay gửi batch trong 5ms để tối ưu throughput.

compression-type=gzip: Nén dữ liệu giúp giảm băng thông.

---
## Consumer
bootstrap-servers: Địa chỉ Kafka broker.

group-id: Tên nhóm consumer (các consumer trong cùng một group sẽ chia sẻ dữ liệu từ Kafka).
auto-offset-reset:

earliest → Đọc từ đầu topic nếu chưa có offset nào.

latest → Đọc từ message mới nhất.

none → Không tìm thấy offset sẽ gây lỗi.

enable-auto-commit=false → Chúng ta sẽ manual commit offset.

---
## Kafka Stream

application-id: Tên ứng dụng Kafka Streams.

state-dir: Thư mục lưu trạng thái khi dùng stateful processing.