import sys
input = sys.stdin.readline

n = int(input())

stack = []
ans = []
now = 1
make = True

for _ in range(n):
  number = int(input())

  while now <= number:
    stack.append(now)
    ans.append('+')
    now += 1

  if stack[-1] == number:
    stack.pop()
    ans.append('-')

  else: 
    print('NO')
    break

else:
  for i in ans:
    print(i)
