## Traffic Light Controller API

### Design Principles
- Invalid states are prevented by design
- Business rules live in the domain layer
- Concurrency will be handled per intersection

### Assumptions
- Fixed directions (N, S, E, W)
- One active phase at a time

### How to Run
