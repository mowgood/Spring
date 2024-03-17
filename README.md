# 🛒 eShop (2023.11 ~ )
마이크로 서비스를 직접 설계하며   
다양한 문제에 직면하여 문제를 해결해 나가고  
여러 가지 기술을 학습 후 활용하기 위해 시작한 개인 프로젝트.

<br>

## 개발 환경
- Java 17
- MySQL 8.0
- Gradle 8.4
- Spring Boot 3.1.5
- Spring Cloud 2022.0.4
- Spring Data JPA
- Spring Security 6.1.5

<br>

## 주요 기능
- User Service
- Order Service
- Product Service

<br>

## 관련 포스팅
- [Spring Cloud Discovery와 Spring Cloud Gateway](https://good-and-great.tistory.com/63)  
- [마이크로서비스 생성과 apigateway 연동](https://good-and-great.tistory.com/69)  
- [인증 인가](https://good-and-great.tistory.com/70)

<br>

## 개발 과정
- 여러 서비스들의 개발 히스토리를 한 눈에 파악하고, 관리하기 쉽도록 하나의 레포지토리로 관리.
  - "[서비스명]" 과 라벨을 이용하여 서비스를 구분.
    
<br>

- 시스템의 지속성과 확장성을 위해 GitHub Actions를 사용하여 테스트 자동화 CI 구축.
  - 모노레포 환경에서 병렬 테스트를 진행하기 위해 각 서비스마다 Job을 개별적으로 실행하도록 함.
  - 변경 사항이 있는 서비스만 테스트 진행.

<br>

- docker-compose를 사용하여 로컬 개발 환경 구축.

<br>
