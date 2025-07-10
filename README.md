# 💳 Receivable Transactions Service

Este proyecto implementa un servicio para registrar y calcular transacciones financieras, como compras con tarjetas de crédito o débito. Se generan registros de cuentas por cobrar (receivables) basados en reglas de negocio predefinidas.

---

## ⚙️ Tecnologías Utilizadas

- Java 17
- Spring Boot
- JPA / Hibernate
- PostgreSQL (puede adaptarse a otros motores SQL)
- Maven

---

## Cómo ejecutar el proyecto

### Requisitos previos
- Java 17 instalado y configurado (variable `JAVA_HOME` apuntando a Java 17)
- Maven instalado y configurado

### Comandos para levantar la aplicación

Para compilar y construir el proyecto:

1. mvn clean install

2. mvn spring-boot:run

```bash

## 🧠 Reglas de Negocio

| Método de Pago | Estado del Receivable | Fecha de Pago   | Comisión |
|----------------|------------------------|------------------|----------|
| Débito         | `paid`                 | Día actual (D+0) | 2%       |
| Crédito        | `waiting_funds`        | D + 30 días      | 4%       |

---

## 🗃️ Esquema de Base de Datos

La siguiente tabla almacena las transacciones procesadas y sus datos relacionados.

### Tabla: `receivable_transactions`

```sql
CREATE TABLE receivable_transactions (
    id VARCHAR(50) PRIMARY KEY,           -- ID de la transacción (UUID o incremental)
    status VARCHAR(20),                   -- Estado: "paid", "waiting_funds", etc.
    create_date DATE,                     -- Fecha de creación (formato yyyy-mm-dd)
    merchantId VARCHAR(20),               -- Id del comerciante.

    -- Datos de la transacción original
    value NUMERIC(12, 2),                 -- Monto total
    description VARCHAR(255),            -- Descripción del producto o servicio
    method VARCHAR(50),                  -- Método de pago: "credit", "debit", etc.
    card_number VARCHAR(20),             -- Últimos dígitos de la tarjeta
    card_holder_name VARCHAR(100),       -- Titular de la tarjeta
    card_expiration_date VARCHAR(7),     -- Fecha de expiración ("MM/yy")
    card_cvv VARCHAR(10),                -- Código de seguridad (CVV)

    -- Datos del receivable (cuenta por cobrar)
    subtotal NUMERIC(12, 2),             -- Subtotal calculado
    discount NUMERIC(12, 2),             -- Descuento aplicado
    total NUMERIC(12, 2),                -- Total final

    payment_day DATE                     -- Fecha estimada de cobro según reglas
);
