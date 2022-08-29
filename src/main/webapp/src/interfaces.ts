export interface Resource {
    type: "bnode" | "uri" | "literal",
    value: string
}

export type Binding = Record<string, Resource>;

export interface QueryResults {
    head: { vars: string[] },
    results: { bindings: Binding[] }
}