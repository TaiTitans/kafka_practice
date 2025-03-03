📌 Điểm quan trọng:
✔ node.id=1 → ID của broker.
✔ listeners → Kafka lắng nghe trên 9091 (nội bộ) và 19091 (bên ngoài).
✔ process.roles=broker,controller → Đây là cả Broker & Controller.
✔ controller.quorum.voters → Danh sách các node quản lý cluster.
✔ log.dirs → Thư mục lưu trữ log Kafka.
✔ num.network.threads / num.io.threads → Tối ưu hiệu suất.
✔ num.partitions=3 → Mỗi topic mặc định có 3 partitions.
✔ log.retention.hours=168 → Kafka giữ dữ liệu trong 7 ngày.

1️⃣ server-1.properties: Broker + Controller
2️⃣ server-2.properties: Chỉ là Broker
3️⃣ server-3.properties: Chỉ là Broker

🚀 Kafka chạy với 3 node, trong đó kafka-1 làm Controller chính. 🔥