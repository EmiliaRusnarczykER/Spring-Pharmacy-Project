import MedicineCard from "@/app/components/Card"

interface PageProps {
  params: {
    category: string
  }
}

export default async function Page ({ params }: PageProps) {
  const { category } = params

  async function getData () {
    const res = await fetch(`http://localhost:8080/api/medicines/category=${category}`, { cache: 'no-cache' })
    if (!res.ok) {
      throw new Error('Failed to fetch data')
    }
    return res.json()
  }

  const data = await getData()
  return (
    <>
      <h1 className="text-5xl p-10">{category.includes('%20') ? category.replace('%20', ' ') : category}</h1>
      <MedicineCard data={data} />
    </>
  )
}