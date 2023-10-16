
import openai
import os
from openai.embeddings_utils import cosine_similarity, get_embedding
import sys


# 获取访问open ai的密钥
openai.api_key = os.getenv("OPENAI_API_KEY")

# 选择使用最小的ada模型
EMBEDDING_MODEL = "text-embedding-ada-002"

# 获取"好评"和"差评"的
positive_review = get_embedding("好评")
negative_review = get_embedding("差评")
data = sys.stdin.readline().strip()

# 提前计算“好评”和“差评”这两个字的 Embedding。
# 而对于任何一段文本评论，我们也都可以通过 API 拿到它的 Embedding。
# 那么，我们把这段文本的 Embedding 和“好评”以及“差评”通过余弦距离（Cosine Similarity）计算出它的相似度。
# 然后我们拿这个 Embedding 和“好评”之间的相似度，去减去和“差评”之间的相似度，就会得到一个分数。如果这个分数大于 0，那么说明我们的评论和“好评”的距离更近，我们就可以判断它为好评。
# 如果这个分数小于 0，那么就是离差评更近，我们就可以判断它为差评。
def get_score(sample_embedding):
    return cosine_similarity(sample_embedding, positive_review) - cosine_similarity(sample_embedding, negative_review)
# 从标准输入读取Java发送的数据

data_e = get_embedding(data)
score = get_score(data_e)
print(openai.api_key)
print(data)
# 在控制台打印接收到的数据
print(score)

