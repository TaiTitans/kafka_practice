# kafka_practice
---

### 🎯 1. Producer & Consumer APIs
Mục tiêu: Biết cách lập trình với Kafka bằng Java

✅ Viết Kafka Producer (Java Spring Boot)

Gửi message với KafkaTemplate.

Sử dụng Keyed Messages để kiểm soát Partitioning.

Cấu hình Producer (acks, retries, batch size, linger.ms, compression).

✅ Viết Kafka Consumer (Java Spring Boot)

Consume message từ Kafka bằng @KafkaListener.

Cấu hình Consumer Offsets (earliest, latest, none).

Xử lý Dead Letter Queue (DLQ) khi có lỗi.

Manual vs. Auto Commit Offset.

✅ Kafka Streams API

Dùng Kafka Streams để xử lý real-time data streaming.

So sánh Kafka Streams vs. Apache Flink vs. Spark Streaming.

### 🎯 2. Quản lý Kafka trong môi trường thực tế
Mục tiêu: Hiểu cách deploy và scale Kafka trong production.

✅ Triển khai Kafka Cluster trên Docker

Cấu hình Kafka Cluster với Multiple Brokers.

Dùng KRaft Mode thay vì ZooKeeper.

✅ Monitoring Kafka với Prometheus + Grafana

Quan trọng: Giám sát Lag, Throughput, Consumer Offsets.

Thiết lập Alert khi Kafka bị lỗi hoặc mất dữ liệu.

✅ Tối ưu hiệu suất Kafka (Performance Tuning)

Cấu hình batch.size, linger.ms, compression.type để tối ưu Producer.

Điều chỉnh fetch.min.bytes, max.poll.records để tối ưu Consumer.

Giảm latency bằng cách tối ưu partitioning strategy.
